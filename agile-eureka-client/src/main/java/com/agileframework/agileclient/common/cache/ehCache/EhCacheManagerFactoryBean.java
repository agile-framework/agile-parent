package com.agileframework.agileclient.common.cache.ehCache;

import com.agileframework.agileclient.common.factory.LoggerFactory;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.Configuration;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;

/**
 * Created by 佟盟 on 2018/2/11
 */
public class EhCacheManagerFactoryBean implements FactoryBean<CacheManager>, InitializingBean, DisposableBean {
    private Log log = LoggerFactory.createLogger("ehcache", EhCacheManagerFactoryBean.class);
    @Nullable
    private CacheManager cacheManager;
    private boolean locallyManaged = true;
    @Nullable
    private String cacheManagerName;
    @Nullable
    private Configuration configLocation;
    private boolean acceptExisting = false;
    private boolean shared = false;

    public void setAcceptExisting(boolean acceptExisting) {
        this.acceptExisting = acceptExisting;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public void setLocallyManaged(boolean locallyManaged) {
        this.locallyManaged = locallyManaged;
    }

    public void setCacheManagerName(String cacheManagerName) {
        this.cacheManagerName = cacheManagerName;
    }

    public void setConfigLocation(Configuration configLocation) {
        this.configLocation = configLocation;
    }

    @Override
    public void destroy() {
        if (this.cacheManager != null && this.locallyManaged) {
            if (log.isInfoEnabled()) {
                log.info("关闭 EhCache CacheManager" + (this.cacheManagerName != null ? " '" + this.cacheManagerName + "'" : ""));
            }
            this.cacheManager.shutdown();
        }
    }

    @Override
    public CacheManager getObject() {
        return this.cacheManager;
    }

    @Override
    public Class<?> getObjectType() {
        return (this.cacheManager != null ? this.cacheManager.getClass() : CacheManager.class);
    }

    @Override
    public void afterPropertiesSet() {
        if (log.isInfoEnabled()) {
            log.info("正在初始化 EhCache CacheManager" + (this.cacheManagerName != null ? " '" + this.cacheManagerName + "'" : ""));
        }

        if (this.cacheManagerName != null) {
            this.configLocation.setName(this.cacheManagerName);
        }

        if (this.shared) {
            this.cacheManager = CacheManager.create(this.configLocation);
        }
        else if (this.acceptExisting) {
            synchronized (CacheManager.class) {
                this.cacheManager = CacheManager.getCacheManager(this.cacheManagerName);
                if (this.cacheManager == null) {
                    this.cacheManager = CacheManager.getInstance();
                }
                else {
                    this.locallyManaged = false;
                }
            }
        }
        else {
            this.cacheManager = new CacheManager(this.configLocation);
        }
    }
}
