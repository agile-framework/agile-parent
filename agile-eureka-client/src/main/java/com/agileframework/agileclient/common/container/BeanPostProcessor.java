package com.agileframework.agileclient.common.container;

import com.agileframework.agileclient.common.annotation.AnnotationProcessor;
import com.agileframework.agileclient.common.util.ArrayUtil;
import com.agileframework.agileclient.common.util.ObjectUtil;
import com.agileframework.agileclient.mvc.model.dao.Dao;
import com.agileframework.agileclient.mvc.model.entity.SysTaskTargetEntity;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by 佟盟 on 2018/1/19
 * bean初始化对象过程
 */
@Component
public class BeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor, ApplicationContextAware {
    private ApplicationContext applicationContext;
    private final Dao dao;

    @Autowired
    public BeanPostProcessor(Dao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        testSysTask(bean);
        methodAnnotationProcessor(bean);
        classAnnotationProcessor(bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 处理自定义注解
     */
    private void methodAnnotationProcessor(Object bean) {
        Method[] methods =  ReflectionUtils.getUniqueDeclaredMethods(bean.getClass());
        for(int i = 0; i < methods.length; i++){
            Method method = methods[i];
            Annotation[] annotations = method.getAnnotations();
            for(int j = 0 ; j < annotations.length;j++){
                Class<? extends Annotation> clazz = annotations[j].annotationType();
                if(ArrayUtil.contains(AnnotationProcessor.methodAnnotations,clazz)){
                    try {
                        Method annotationMethod = AnnotationProcessor.class.getDeclaredMethod(clazz.getSimpleName(), clazz, Object.class,Method.class);
                        annotationMethod.setAccessible(true);
                        ReflectionUtils.invokeMethod(annotationMethod,applicationContext.getBean(AnnotationProcessor.class),annotations[j],bean,method);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 处理自定义注解
     */
    private void classAnnotationProcessor(Object bean){
        Class<?> clazz = bean.getClass();
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        for(int i = 0 ; i < annotations.length;i++){
            Class<? extends Annotation> annotationClazz = annotations[i].annotationType();
            if(ArrayUtil.contains(AnnotationProcessor.afterClassAnnotations,annotationClazz)){
                try {
                    Method annotationMethod = AnnotationProcessor.class.getDeclaredMethod(annotationClazz.getSimpleName(), annotationClazz, Object.class);
                    annotationMethod.setAccessible(true);
                    ReflectionUtils.invokeMethod(annotationMethod,applicationContext.getBean(AnnotationProcessor.class),annotations[i],bean);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 检测任务
     */
    void testSysTask(Object bean){
        Class<?> clazz = bean.getClass();
        Package packageInfo = clazz.getPackage();
        if(ObjectUtil.isEmpty(packageInfo))return;
        String packageName = packageInfo.getName();
        if(!packageName.startsWith("com.agile.mvc.service"))return;
        Method[] methods =  clazz.getDeclaredMethods();
        for(int i = 0; i < methods.length; i++){
            Method method = methods[i];
            String methodName = method.getName();
            String[] excludeMethod = {"save","delete","update","query"};
            if(ArrayUtil.contains(excludeMethod,methodName))continue;
            String id = clazz.getName() + "." + methodName;
            SysTaskTargetEntity entity = dao.findOne(SysTaskTargetEntity.class, id);
            if(!ObjectUtil.isEmpty(entity))continue;
            SysTaskTargetEntity sysTaskTargetEntity = new SysTaskTargetEntity();
            sysTaskTargetEntity.setSysTaskTargetId(id);
            sysTaskTargetEntity.setTargetPackage(packageName);
            sysTaskTargetEntity.setTargetClass(clazz.getSimpleName());
            sysTaskTargetEntity.setTargetMethod(methodName);
            sysTaskTargetEntity.setName(id);
            dao.update(sysTaskTargetEntity);
        }
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
