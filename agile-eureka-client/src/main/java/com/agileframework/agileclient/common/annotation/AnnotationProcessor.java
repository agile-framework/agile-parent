package com.agileframework.agileclient.common.annotation;

import com.agileframework.agileclient.common.util.ArrayUtil;
import com.agileframework.agileclient.common.util.ObjectUtil;
import com.agileframework.agileclient.common.util.StringUtil;
import com.sun.istack.internal.NotNull;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佟盟 on 2018/1/5
 */
@Component
public class AnnotationProcessor implements EnvironmentAware {
    private Environment env;
    /**
     * 准备处理的注解
     */
    public static Class[] beforeClassAnnotations = {Properties.class};
    public static Class[] afterClassAnnotations = {};
    public static Class[] methodAnnotations = {Init.class};

    void Init(Init init, Object bean, Method method){
        method.setAccessible(true);
        if(!ObjectUtil.isEmpty(init)){
            try {
                method.invoke(bean);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    void Properties(Properties properties, Object bean) throws InstantiationException, IllegalAccessException {
        Class<?> targetClass = bean.getClass();
        if(ObjectUtil.isEmpty(properties))return;
            String prefix = properties.prefix();
            setProperties(bean,prefix);
    }

    private void setProperties(Object target,String prefix) throws IllegalAccessException, InstantiationException {
        if(ObjectUtil.isEmpty(target))return;
        Class<?> targetClass = target.getClass();
        Field[] fields = targetClass.getDeclaredFields();
        for(int i = 0 ; i < fields.length;i++){
            Field field = fields[i];
            field.setAccessible(true);
            Class<?> type = field.getType();
            String name = field.getName();
            if(type.isAssignableFrom(List.class)){
                Type genericType = field.getGenericType();
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                if(ArrayUtil.isEmpty(typeArguments))continue;
                Class innerClass = (Class)typeArguments[0];


                List list = new ArrayList();
                int j = 0 ;
                boolean hasNext = true;

                if(ObjectUtil.isEmpty(innerClass.getPackage()) || innerClass.getPackage().getName().startsWith("java.")){
                    while (hasNext){
                        String key = prefix+"."+name+"["+j+"]";
                        if(j==0){
                            key = env.containsProperty(prefix+"."+name+"["+j+"]")?prefix+"."+name+"["+j+"]":prefix+"."+name;
                        }
                        if(env.containsProperty(key)){
                            Object temp = env.getProperty(key,innerClass);
                            list.add(temp);
                            j++;
                        }else{
                            hasNext = false;
                        }
                    }
                }else{
                    while (hasNext){
                        Object temp = innerClass.newInstance();
                        setProperties(temp,prefix+"."+name+"["+j+++"]");
                        if(ObjectUtil.compareValue(temp,innerClass.newInstance()) && j==1){
                            setProperties(temp,prefix+"."+name);
                        }
                        if(ObjectUtil.compareValue(temp,innerClass.newInstance()) && j!=1){
                            hasNext = false;
                            continue;
                        }
                        list.add(temp);
                    }
                }
                field.set(target,list);
            }else{
                if(ObjectUtil.isEmpty(type.getPackage()) || type.getPackage().getName().startsWith("java.")){
                    String key = prefix + "." + StringUtil.camelToUnderline(name);
                    if(env.containsProperty(key))
                    field.set(target,env.getProperty(key,type));
                }else{
                    Object temp = type.newInstance();
                    setProperties(temp,prefix+"."+ StringUtil.camelToUnderline(name));
                    field.set(target,temp);
                }
            }
        }
    }

    @Override
    public void setEnvironment(@NotNull Environment environment) {
        this.env = environment;
    }
}
