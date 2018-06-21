package com.agileframework.agileclient.common.cache.redis;

import com.agileframework.agileclient.common.configure.RedisConfig;
import com.agileframework.agileclient.common.factory.LoggerFactory;
import org.apache.commons.logging.Log;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.cfg.spi.DomainDataRegionBuildingContext;
import org.hibernate.cache.cfg.spi.DomainDataRegionConfig;
import org.hibernate.cache.spi.support.DomainDataStorageAccess;
import org.hibernate.cache.spi.support.RegionFactoryTemplate;
import org.hibernate.cache.spi.support.RegionNameQualifier;
import org.hibernate.cache.spi.support.StorageAccess;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 佟盟 on 2018/5/10
 */
@Component
public class RedisRegionFactory extends RegionFactoryTemplate {
    private static final Log log = LoggerFactory.createLogger("redis", RedisRegionFactory.class);
    private static final AtomicInteger REFERENCE_COUNT = new AtomicInteger();
    private volatile RedisCacheManager cacheManager;
    private RedisCacheManager redisCacheManager;


    @Override
    protected DomainDataStorageAccess createDomainDataStorageAccess(
            DomainDataRegionConfig regionConfig,
            DomainDataRegionBuildingContext buildingContext) {
        return new StorageAccessImpl((RedisCache)getOrCreateCache( regionConfig.getRegionName(), buildingContext.getSessionFactory() ));
    }
    @Override
    protected StorageAccess createQueryResultsRegionStorageAccess(String regionName, SessionFactoryImplementor sessionFactory) {
        return new StorageAccessImpl( (RedisCache)getOrCreateCache( regionName, sessionFactory ) );
    }

    @Override
    protected StorageAccess createTimestampsRegionStorageAccess(String regionName, SessionFactoryImplementor sessionFactory) {
        return new StorageAccessImpl((RedisCache) getOrCreateCache( regionName, sessionFactory ));
    }

    @Override
    protected void prepareForUse(SessionFactoryOptions settings, Map configValues) {
        synchronized ( this ) {
            this.cacheManager = (RedisCacheManager)resolveCacheManager( settings, configValues );
            if ( this.cacheManager == null ) {
                throw new CacheException( "Could not start Ehcache CacheManager" );
            }
        }
    }

    @Override
    protected void releaseFromUse() {
        cacheManager = null;
    }

    private Cache getOrCreateCache(String unqualifiedRegionName, SessionFactoryImplementor sessionFactory) {
        verifyStarted();
//        assert !RegionNameQualifier.INSTANCE.isQualified( unqualifiedRegionName, sessionFactory.getSessionFactoryOptions() );

        final String qualifiedRegionName = RegionNameQualifier.INSTANCE.qualify(
                unqualifiedRegionName,
                sessionFactory.getSessionFactoryOptions()
        );

        final Cache cache = cacheManager.getCache( qualifiedRegionName );
        if ( cache == null ) {
            throw new CacheException( "未成功获取区域 [" + qualifiedRegionName + "]缓存对象" );
        }
        return cache;
    }

    private CacheManager resolveCacheManager(SessionFactoryOptions settings, Map properties) {
        return useExplicitCacheManager( settings, properties );
    }

    private CacheManager useExplicitCacheManager(SessionFactoryOptions settings, Map properties) {
        try {
            if(log.isInfoEnabled()){
                log.info("初始化Redis二级缓存区域");
            }
            initConnectionFactory();
            REFERENCE_COUNT.incrementAndGet();
            return redisCacheManager;
        }catch (Exception e){
            if(log.isInfoEnabled()){
                log.info("初始化Redis二级缓存区域失败");
            }
            REFERENCE_COUNT.decrementAndGet();
            throw e;
        }
    }

    private void initConnectionFactory(){
        RedisConfig redisConfig = new RedisConfig();
        JedisPoolConfig jedisPoolConfig = redisConfig.redisPool();
        JedisConnectionFactory jedisConnectionFactory = redisConfig.jedisConnectionFactory(jedisPoolConfig);
        this.redisCacheManager = redisConfig.redisCacheManager(jedisConnectionFactory);
    }
}
