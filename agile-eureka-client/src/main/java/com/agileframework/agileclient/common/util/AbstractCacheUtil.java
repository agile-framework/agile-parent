package com.agileframework.agileclient.common.util;

/**
 * Created by 佟盟 on 2017/5/18
 */
public interface AbstractCacheUtil {
    void setCache(String key, Object object);
    void setCache(String key, Object object, int timeToIdleSeconds);
    void removeCache(String key);
    void removeAll();
    Object getCache(String key);
}
