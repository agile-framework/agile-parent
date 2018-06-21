package com.agileframework.agileclient.common.configure;

import com.agileframework.agileclient.common.cache.ehCache.EhCacheManagerFactoryBean;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 佟盟 on 2017/10/8
 */
@Configuration
public class EhCacheConfig {

    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {
        return new EhCacheCacheManager(ehCacheManagerFactoryBean.getObject());
    }

    @Bean
    EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setAcceptExisting(true);
        ehCacheManagerFactoryBean.setConfigLocation(configuration());
        return ehCacheManagerFactoryBean;
    }

    public static net.sf.ehcache.config.Configuration configuration(){
        String path = System.getProperty("agile.root") + "temp";
        DiskStoreConfiguration diskStoreConfiguration = new DiskStoreConfiguration().path(path);
        new PersistenceConfiguration().strategy("localTempSwap");
        net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration()

                //设置缓存目录
                .diskStore(diskStoreConfiguration)
                //指定除自身之外的网络群体中其他提供同步的主机列表，用“|”分开不同的主机
//                .cacheManagerPeerProviderFactory(new FactoryConfiguration<FactoryConfiguration<?>>()
//                        .className(RMICacheManagerPeerProviderFactory.class.getName())
//                        .properties("peerDiscovery=manual,rmiUrls=//localhost:40004/metaCache|//localhost:40005/metaCache")//
//                )
                //配宿主主机配置监听程序
//                .cacheManagerPeerListenerFactory(new FactoryConfiguration<FactoryConfiguration<?>>()//
//                        .className(RMICacheManagerPeerListenerFactory.class.getName())//
//                        .properties("port=40004,socketTimeoutMillis=2000")//
//                )
                .defaultCache(new CacheConfiguration()
                        .maxEntriesLocalHeap(10000)
                        .timeToIdleSeconds(120)
                        .timeToLiveSeconds(120)
                        .maxEntriesLocalDisk(10000000)
                        .diskExpiryThreadIntervalSeconds(120)
                        .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU))
//                .cache(new CacheConfiguration("agileCache", 10000)//缓存名称(必须唯一),maxElements内存最多可以存放的元素的数量
//                                .overflowToDisk(true)
//                                .diskPersistent(true)
//                                .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)//清理机制：LRU最近最少使用 FIFO先进先出 LFU较少使用
//                                .timeToIdleSeconds(1000)//元素最大闲置时间
//                                .timeToLiveSeconds(2000)//元素最大生存时间
//                                .eternal(false)//元素是否永久缓存
//                                .diskExpiryThreadIntervalSeconds(120)//缓存清理时间(默认120秒)
//                                //LOCALTEMPSWAP当缓存容量达到上限时，将缓存对象（包含堆和非堆中的）交换到磁盘中
//                                //NONE当缓存容量达到上限时，将缓存对象（包含堆和非堆中的）交换到磁盘中
//                                //DISTRIBUTED按照_terracotta标签配置的持久化方式执行。非分布式部署时，此选项不可用
//                                .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.NONE)).maxEntriesLocalDisk(0)//磁盘中最大缓存对象数0表示无穷大)
//                        .cacheEventListenerFactory(new CacheConfiguration.CacheEventListenerFactoryConfiguration().className(RMICacheReplicatorFactory.class.getName()))
//                )
                .cache(new CacheConfiguration("agileCache", 10000)
                        .maxEntriesLocalHeap(1000)
                        .timeToIdleSeconds(300)
                        .timeToLiveSeconds(600)
                        .diskExpiryThreadIntervalSeconds(600))
                .cache(new CacheConfiguration("securityCache", 10000)
                        .maxEntriesLocalHeap(1000)
                        .timeToIdleSeconds(300)
                        .timeToLiveSeconds(600)
                        .diskExpiryThreadIntervalSeconds(600))
                .cache(new CacheConfiguration("hibernate.org.hibernate.cache.spi.TimestampsRegion", 10000)
                        .maxEntriesLocalHeap(5)
                        .timeToLiveSeconds(120))
                .cache(new CacheConfiguration("hibernate.org.hibernate.cache.spi.QueryResultsRegion", 10000)
                        .maxEntriesLocalHeap(5000)
                        .eternal(true));
        configuration.setName("agileManager");
        return configuration;
    }
}
