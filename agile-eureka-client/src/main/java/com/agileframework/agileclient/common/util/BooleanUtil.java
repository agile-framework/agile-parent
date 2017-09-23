package com.agileframework.agileclient.common.util;


/**
 * Created by mydeathtrial on 2017/4/19
 */
public class BooleanUtil{
    public static boolean toBoolean(String resource){
        return "yes".equals(resource.toLowerCase()) || "true".equals(resource.toLowerCase()) || "1".equals(resource);
    }

    public static boolean toBoolean(Object resource){
        return ObjectUtil.isEmpty(resource)?false:(Boolean)resource;
    }
}
