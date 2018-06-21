package com.agileframework.agileclient.common.util;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;

/**
 * Created by 佟盟 on 2017/5/19
 */
public class CacheUtil {

    private static Cache cache;

    public static Cache getCache(){
        if (ObjectUtil.isEmpty(cache)){
            cache = FactoryUtil.getBean(CacheManager.class).getCache("agileCache");
        }
        return cache;
    }
    public static Long setNx(String name,String time){
        JedisConnectionFactory jedisConnectionFactory = FactoryUtil.getBean(JedisConnectionFactory.class);
        if(!ObjectUtil.isEmpty(jedisConnectionFactory)){
            return ((Jedis)jedisConnectionFactory.getConnection().getNativeConnection()).setnx(name,time);
        }
        return 1L;
    }

    public static void expire(String name, int time){
        JedisConnectionFactory jedisConnectionFactory = FactoryUtil.getBean(JedisConnectionFactory.class);
        if(!ObjectUtil.isEmpty(jedisConnectionFactory)){
            ((Jedis)jedisConnectionFactory.getConnection().getNativeConnection()).expire(name, time);
        }
    }

}
