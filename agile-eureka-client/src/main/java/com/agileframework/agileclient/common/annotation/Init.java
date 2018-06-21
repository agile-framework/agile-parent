package com.agileframework.agileclient.common.annotation;

import java.lang.annotation.*;

/**
 * Created by 佟盟 on 2017/10/18
 * 伴随spring容器初始化时触发方法注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Init {
}
