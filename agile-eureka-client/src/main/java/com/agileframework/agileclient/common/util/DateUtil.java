package com.agileframework.agileclient.common.util;

import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * Created by 佟盟 on 2017/7/13
 */
public class DateUtil extends DateUtils {
    /**
     * 获取Long型时间戳
     */
    public static long getTimeStamp(){
        return getTimeStamp(new Date());
    }

    /**
     * 获取Long型时间戳
     */
    public static long getTimeStamp(Date date){
        return date.getTime();
    }

    /**
     * 获取时间戳字符串
     */
    public static String getTimeStampStr(){
        return getTimeStampStr(new Date());
    }

    /**
     * 获取时间戳字符串
     */
    public static String getTimeStampStr(Date date){
        return Long.toString(date.getTime());
    }
}
