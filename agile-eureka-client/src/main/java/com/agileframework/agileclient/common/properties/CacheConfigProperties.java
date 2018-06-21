package com.agileframework.agileclient.common.properties;

import com.agileframework.agileclient.common.annotation.Properties;

/**
 * Created by 佟盟 on 2018/2/1
 */
@Properties(prefix = "agile.cache")
public class CacheConfigProperties {
    private static String proxy = "ehcache";

    public static String getProxy() {
        return proxy;
    }

    public static void setProxy(String proxy) {
        CacheConfigProperties.proxy = proxy;
    }
}
