package com.agileframework.agileclient.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by 佟盟 on 2018/1/5
 * 配置属性集注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
public @interface Properties {
    String prefix() default "";
}
