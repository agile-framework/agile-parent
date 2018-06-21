package com.agileframework.agileclient.common.util;

import org.apache.commons.collections.MapUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by 佟盟 on 2017/12/22
 */
public class MapUtil extends MapUtils {
    public static void coverMap(Map<String,Object> map, Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        for(int i = 0 ; i < fields.length;i++){
            try {
                Field field = fields[i];
                field.setAccessible(true);
                Object value = field.get(object);
                map.put(field.getName(), value);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static boolean isMap(Object o){
        return o instanceof Map;
    }
}
