package com.agileframework.agileclient.common.configure;

import com.agileframework.agileclient.common.annotation.ExcludeComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by 佟盟 on 2017/9/26
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
@EnableCaching
@EnableScheduling
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeComponentScan.class)})
@PropertySources({
        @PropertySource(ignoreResourceNotFound = true,value = "classpath:agile.properties",encoding = "UTF-8"),
        @PropertySource(ignoreResourceNotFound = true,value = "classpath:bootstrap.properties",encoding = "UTF-8"),
        @PropertySource(ignoreResourceNotFound = true,value = "classpath:application.properties",encoding = "UTF-8"),
        @PropertySource(ignoreResourceNotFound = true,value = "classpath:application.yml",encoding = "UTF-8"),
})
public class SpringConfig {

}
