package com.agileframework.agileclient.common.properties;

import com.agileframework.agileclient.common.annotation.Properties;

import java.util.List;

/**
 * Created by 佟盟 on 2018/1/5
 */
@Properties(prefix = "agile.redis")
public class RedisConfigProperties {
    private static String pass;
    private static int maxIdle;
    private static int minIdle;
    private static int maxWaitMillis;
    private static boolean testOnReturn;
    private static boolean testOnBorrow;
    private static List<String> host;
    private static List<Integer> port;

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        RedisConfigProperties.pass = pass;
    }

    public static int getMaxIdle() {
        return maxIdle;
    }

    public static void setMaxIdle(int maxIdle) {
        RedisConfigProperties.maxIdle = maxIdle;
    }

    public static int getMinIdle() {
        return minIdle;
    }

    public static void setMinIdle(int minIdle) {
        RedisConfigProperties.minIdle = minIdle;
    }

    public static int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public static void setMaxWaitMillis(int maxWaitMillis) {
        RedisConfigProperties.maxWaitMillis = maxWaitMillis;
    }

    public static boolean isTestOnReturn() {
        return testOnReturn;
    }

    public static void setTestOnReturn(boolean testOnReturn) {
        RedisConfigProperties.testOnReturn = testOnReturn;
    }

    public static boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public static void setTestOnBorrow(boolean testOnBorrow) {
        RedisConfigProperties.testOnBorrow = testOnBorrow;
    }

    public static List<String> getHost() {
        return host;
    }

    public static void setHost(List<String> host) {
        RedisConfigProperties.host = host;
    }

    public static List<Integer> getPort() {
        return port;
    }

    public static void setPort(List<Integer> port) {
        RedisConfigProperties.port = port;
    }
}
