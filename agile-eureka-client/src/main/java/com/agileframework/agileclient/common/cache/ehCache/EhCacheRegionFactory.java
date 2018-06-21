package com.agileframework.agileclient.common.cache.ehCache;

import com.agileframework.agileclient.common.configure.EhCacheConfig;
import com.agileframework.agileclient.common.factory.LoggerFactory;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import org.apache.commons.logging.Log;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.ehcache.internal.SingletonEhcacheRegionFactory;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 佟盟 on 2018/1/4
 */
public class EhCacheRegionFactory extends SingletonEhcacheRegionFactory {
    private static final AtomicInteger REFERENCE_COUNT = new AtomicInteger();
    private Log log = LoggerFactory.createLogger("ehcache", EhCacheRegionFactory.class);

    @Override
    protected CacheManager resolveCacheManager(SessionFactoryOptions settings, Map properties) {
        try {
            if(log.isInfoEnabled()){
                log.info("初始化EhCache二级缓存区域");
            }
            REFERENCE_COUNT.incrementAndGet();
            return CacheManager.create(EhCacheConfig.configuration());
        }catch (Exception e){
            if(log.isInfoEnabled()){
                log.info("初始化EhCache二级缓存区域失败");
            }
            REFERENCE_COUNT.decrementAndGet();
            throw e;
        }
    }

    @Override
    protected Cache createCache(String regionName) {
        CacheManager cacheManager = CacheManager.getInstance();
        Cache cache = new Cache(new CacheConfiguration(regionName, 10000)
                .maxEntriesLocalHeap(1000)
                .timeToIdleSeconds(300)
                .timeToLiveSeconds(600)
                .diskExpiryThreadIntervalSeconds(600));
        cacheManager.addCache(cache);
        return cache;
    }
}
