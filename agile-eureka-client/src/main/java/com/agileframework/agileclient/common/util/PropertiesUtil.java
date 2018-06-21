package com.agileframework.agileclient.common.util;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by mydeathtrial on 2017/3/11
 */
@Component
public class PropertiesUtil {

    @Resource
    private Environment environment;

    private Properties properties;

    private static PropertiesUtil propertiesUtil;

    /**
     * 初始化前
     */
    @PostConstruct
    private void init() {
        propertiesUtil = this;
    }

    public PropertiesUtil() {}

    public PropertiesUtil(File file) {
        try {
            this.properties = new Properties();
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            URL url = PropertiesUtil.class.getResource("/com/agile/configuer/agile.properties");
            if(url==null){
                url = PropertiesUtil.class.getResource("/agile.properties");
            }
            propertiesUtil = new PropertiesUtil(new File(url.toURI()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static Properties getPropertys(){
        return propertiesUtil.properties;
    }
    /**
     * 获取工程配置信息
     * @param key 句柄
     * @return 值
     */
    public static String getProperty(String key){
        if(!ObjectUtil.isEmpty(propertiesUtil.environment)){
            return propertiesUtil.environment.getProperty(key);
        }
        return propertiesUtil.properties.getProperty(key);
    }

    public static String getProperty(String var1, String var2){
        return propertiesUtil.getProperty(var1, var2);
    }

    public static <T> T getProperty(String var1, Class<T> var2){
        return (T) ObjectUtil.cast(var2,propertiesUtil.getProperty(var1));
    }

    public static <T> T getProperty(String var1, Class<T> var2, T var3){
        return propertiesUtil.getProperty(var1, var2,var3);
    }

    public static String getRequiredProperty(String var1){
        return propertiesUtil.getRequiredProperty(var1);
    }

    public static <T> T getRequiredProperty(String var1, Class<T> var2){
        return propertiesUtil.getRequiredProperty(var1,var2);
    }

    public static String resolvePlaceholders(String var1){
        return propertiesUtil.resolvePlaceholders(var1);
    }

    public static String resolveRequiredPlaceholders(String var1){
        return propertiesUtil.resolveRequiredPlaceholders(var1);
    }

    public static boolean containsProperty(String var1){
        return propertiesUtil.containsProperty(var1);
    }

}
