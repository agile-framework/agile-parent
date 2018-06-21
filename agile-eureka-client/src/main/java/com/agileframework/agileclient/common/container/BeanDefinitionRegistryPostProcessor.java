package com.agileframework.agileclient.common.container;

import com.agileframework.agileclient.common.annotation.AnnotationProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by 佟盟 on 2018/1/19
 * bean定义过程
 */
@Component
public class BeanDefinitionRegistryPostProcessor implements org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor, EnvironmentAware, ApplicationContextAware {
    private Environment environment;
    private ApplicationContext applicationContext;
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        this.cacheManagerProcessor(beanDefinitionRegistry);
        this.annotationProcessor();
    }
    
    /**
     * 解决多个cacheManager导致spring无法成功获取缓存控制器问题
     */
    private void cacheManagerProcessor(BeanDefinitionRegistry beanDefinitionRegistry){
        String cacheProxy = environment.getProperty("agile.cache.proxy").toLowerCase();

        switch (cacheProxy){
            case "redis":
                beanDefinitionRegistry.removeBeanDefinition("ehCacheCacheManager");
                beanDefinitionRegistry.removeBeanDefinition("ehCacheManagerFactoryBean");
                break;
            case "ehcache":
                beanDefinitionRegistry.removeBeanDefinition("redisCacheManager");
                beanDefinitionRegistry.removeBeanDefinition("redisTemplate");
                beanDefinitionRegistry.removeBeanDefinition("jedisConnectionFactory");
                beanDefinitionRegistry.removeBeanDefinition("redisPool");
                break;
        }
    }

    /**
     * 1：处理自定义注解
     */
    private void annotationProcessor(){
        for(int i = 0; i < AnnotationProcessor.beforeClassAnnotations.length; i++){
            this.annotationPricessor(AnnotationProcessor.beforeClassAnnotations[i]);
        }
    }

    /**
     * 2：处理自定义注解
     */
    private <T extends Annotation> void annotationPricessor(Class<T> clazz){
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(clazz);
        for(Map.Entry<String, Object> map: beans.entrySet()){
            Object bean = map.getValue();
            Class<?> targetClass = bean.getClass();
            T annotation = targetClass.getAnnotation(clazz);
            try {
                Method method = AnnotationProcessor.class.getDeclaredMethod(clazz.getSimpleName(), clazz, Object.class);
                method.setAccessible(true);
                ReflectionUtils.invokeMethod(method,applicationContext.getBean(AnnotationProcessor.class),annotation,bean);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
