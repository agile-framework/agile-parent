package com.agileframework.agileclient.common.configure;

import com.agileframework.agileclient.common.properties.KaptchaConfigProperties;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by 佟盟 on 2017/10/7
 */
@Configuration
public class KaptchaConfig {

    @Bean
    DefaultKaptcha defaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(new Config(properties()));
        return defaultKaptcha;
    }

    private Properties properties(){
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", KaptchaConfigProperties.getBorder());
        properties.setProperty("kaptcha.border.color",KaptchaConfigProperties.getBorderColor());
        properties.setProperty("kaptcha.textproducer.font.color",KaptchaConfigProperties.getTextproducerFontColor());
        properties.setProperty("kaptcha.textproducer.font.size",KaptchaConfigProperties.getTextproducerFontSize());
        properties.setProperty("kaptcha.image.width",KaptchaConfigProperties.getImageWidth());
        properties.setProperty("kaptcha.image.height",KaptchaConfigProperties.getImageHeight());
        properties.setProperty("kaptcha.textproducer.char.length",KaptchaConfigProperties.getTextproducerCharLength());
        properties.setProperty("kaptcha.textproducer.font.names",KaptchaConfigProperties.getTextproducerFontNames());
        return properties;
    }
}
