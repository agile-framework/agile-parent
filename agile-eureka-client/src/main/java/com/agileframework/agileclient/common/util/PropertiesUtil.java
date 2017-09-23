package com.agileframework.agileclient.common.util;

import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by mydeathtrial on 2017/3/11
 */
@Component
public class PropertiesUtil {

    private Properties properties;

    private static PropertiesUtil propertiesUtil;

    /**
     * 构造函数，读取相对路径下的参数文件
     */
    private PropertiesUtil() {
//        try {
//            this.properties = new Properties();
//            InputStream in = new BufferedInputStream(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"com/agile/configure/agile.properties"));
//            properties.load(in);
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 构造函数，读取相对路径下的参数文件
     */
    public PropertiesUtil(String url) {
        try {
            this.properties = new Properties();
            InputStream in = new BufferedInputStream(new FileInputStream(url));
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取单利模式工具对象
     * @return 工具对象
     */
    private static PropertiesUtil getObject(){
        if(ObjectUtil.isEmpty(PropertiesUtil.propertiesUtil)){
            PropertiesUtil.propertiesUtil = new PropertiesUtil();
        }
        return PropertiesUtil.propertiesUtil;
    }

    /**
     * 根据key获取参数文件当中value值
     * @param key key值
     * @return value值
     */
    public static String getProperties(String key){
        PropertiesUtil propertiesUtil = PropertiesUtil.getObject();
        return propertiesUtil.properties.getProperty(key);
    }

    /**
     * 根据key获取参数文件当中value值
     * @param key key值
     * @return value值
     */
    public String getProperty(String key){
        return this.properties.getProperty(key);
    }

    public static Properties cast(Map<String,String> map){
        Properties p = new Properties();
        for (Map.Entry<String,String> entity : map.entrySet()) {
            p.setProperty(entity.getKey(),entity.getValue());
        }
        return p;
    }
}
