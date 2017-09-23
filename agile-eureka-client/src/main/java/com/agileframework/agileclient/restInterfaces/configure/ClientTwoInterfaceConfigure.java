package com.agileframework.agileclient.restInterfaces.configure;

import com.agileframework.agileclient.common.annotation.ExcludeComponentScan;
import feign.Contract;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 佟盟 on 2017/8/27
 */
@Configuration
@ExcludeComponentScan
public class ClientTwoInterfaceConfigure {

    @Bean
    public Contract feignContract() {
        return new SpringMvcContract();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin", "admin");
    }
}
