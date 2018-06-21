package com.agileframework.agileclient.common.properties;

import com.agileframework.agileclient.common.annotation.Properties;

import java.util.List;

/**
 * Created by 佟盟 on 2018/1/11
 */
@Properties(prefix = "agile")
public class DBConfigProperties {
    private static List<DruidConfigProperty> druid;
    private static List<JPAConfigProperty> jpa;

    public static List<DruidConfigProperty> getDruid() {
        return druid;
    }

    public static void setDruid(List<DruidConfigProperty> druid) {
        DBConfigProperties.druid = druid;
    }

    public static List<JPAConfigProperty> getJpa() {
        return jpa;
    }

    public static void setJpa(List<JPAConfigProperty> jpa) {
        DBConfigProperties.jpa = jpa;
    }
}
