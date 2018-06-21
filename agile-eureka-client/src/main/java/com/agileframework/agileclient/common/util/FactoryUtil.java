package com.agileframework.agileclient.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by mydeathtrial on 2017/3/10
 */
@Component
public final class FactoryUtil {

    private static FactoryUtil factoryUtil;
    private final ApplicationContext applicationContext;

    /**
     * 私有化构造器
     * @param applicationContext spring上下文
     */
    @Autowired
    private FactoryUtil(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 初始化前
     */
    @PostConstruct
    private void init() {
        factoryUtil = this;
    }

    /**
     * 根据bean名获取bean对象
     * @param beanName bean名
     * @return bean对象
     */
    public static Object getBean(String beanName) throws BeansException {
        return factoryUtil.applicationContext.getBean(beanName.substring(0,1).toLowerCase()+beanName.substring(1));
    }

    /**
     * 根据bean名获取bean对象
     * @param clazz bean类型
     * @return bean对象
     */
    public static <T> T getBean(Class<T> clazz){
        return factoryUtil.applicationContext.getBean(clazz);
    }

    /**
     * 根据类型查询bean对象
     * @param var1 bean类型
     * @return bean对象
     */
    public static String[] getBeanNamesForType(Class<?> var1){
        return factoryUtil.applicationContext.getBeanNamesForType(var1);
    }

}
