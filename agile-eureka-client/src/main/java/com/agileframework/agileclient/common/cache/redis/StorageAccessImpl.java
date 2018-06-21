package com.agileframework.agileclient.common.cache.redis;

import com.agileframework.agileclient.common.factory.LoggerFactory;
import org.apache.commons.logging.Log;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.support.DomainDataStorageAccess;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;

/**
 * Created by 佟盟 on 2018/5/10
 */
public class StorageAccessImpl implements DomainDataStorageAccess {
    private Log log = LoggerFactory.createLogger("redis", StorageAccessImpl.class);
    private final RedisCache cache;

    StorageAccessImpl(RedisCache cache) {
        this.cache = cache;
    }

    public RedisCache getCache() {
        return cache;
    }

    @Override
    public Object getFromCache(Object key, SharedSessionContractImplementor session) {
        try {
            final Cache.ValueWrapper element = getCache().get( key );
            if ( element == null ) {
                return null;
            }
            else {
                return element.get();
            }
        }
        catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error("redis缓存提取失败");
            }
            throw new CacheException( e );
        }
    }

    @Override
    public void putIntoCache(Object key, Object value, SharedSessionContractImplementor session) {
        try {
            getCache().put(key,value);
        }
        catch (IllegalArgumentException | IllegalStateException e) {
            throw new CacheException( e );
        }
        catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error("redis缓存存放失败");
            }
            throw new CacheException( e );
        }
    }

    @Override
    public boolean contains(Object key) {
        return getCache().get(key)!=null;
    }

    @Override
    public void evictData() {
        try {
            getCache().clear();
        }catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error("redis缓存清空数据失败");
            }
            throw new CacheException( e );
        }
    }

    @Override
    public void evictData(Object key) {
        try {
            getCache().get(key);
        }catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error("redis缓存删除数据失败");
            }
            throw new CacheException( e );
        }
    }

    @Override
    public void release() {
        try {
            getCache().clear();
        }catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error("redis缓存删除数据失败");
            }
            throw new CacheException( e );
        }
    }
}
