package com.agileframework.agileclient.common.annotation;

import java.lang.annotation.*;

/**
 * Created by 佟盟 on 2018/2/2
 * 备注注解
 */
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Remark {
    String value();
}
