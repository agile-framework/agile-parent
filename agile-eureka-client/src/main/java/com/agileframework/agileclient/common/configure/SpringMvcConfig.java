package com.agileframework.agileclient.common.configure;

import com.agileframework.agileclient.common.properties.SpringMVCProperties;
import com.agileframework.agileclient.common.viewResolver.JsonViewResolver;
import com.agileframework.agileclient.common.viewResolver.JumpViewResolver;
import com.agileframework.agileclient.common.viewResolver.PlainViewResolver;
import com.agileframework.agileclient.common.viewResolver.XmlViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 佟盟 on 2017/8/22
 */
@Configuration
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.setOrder(-1).addResourceHandler("/static/**","/favicon.ico")
                .addResourceLocations("classpath:com/agile/static/","classpath:com/agile/static/img/","classpath:com/agile/static/plus/jquery/","classpath:com/agile/static/plus/swagger/");
    }

    /**
     * 视图解析器
     * 配置视图解析器视图列表
     * @param manager 略
     * @return 视图解析器
     */
    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager){
        List<ViewResolver> list = new ArrayList<>();
        list.add(new JsonViewResolver());
        list.add(new XmlViewResolver());
        list.add(new PlainViewResolver());
        list.add(new JumpViewResolver());

        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        viewResolver.setContentNegotiationManager(manager);
        viewResolver.setViewResolvers(list);
        return viewResolver;
    }

    /**
     * 文件上传配置
     * @return 文件上传下载解析器
     */
    @Bean
    public CommonsMultipartResolver contentCommonsMultipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(SpringMVCProperties.getUpload().getMaxUploadSize());
        resolver.setDefaultEncoding(SpringMVCProperties.getUpload().getDefaultEncoding());
        return resolver;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        Map<String, MediaType> map = new HashMap<>();
        map.put("atom", MediaType.APPLICATION_ATOM_XML);
        map.put("form", MediaType.APPLICATION_FORM_URLENCODED);
        map.put("json", MediaType.APPLICATION_JSON);
        map.put("octet-stream", MediaType.APPLICATION_OCTET_STREAM);
        map.put("event-stream", MediaType.TEXT_EVENT_STREAM);
        map.put("pdf", MediaType.APPLICATION_PDF);
        map.put("xhtml", MediaType.APPLICATION_XHTML_XML);
        map.put("gif", MediaType.IMAGE_GIF);
        map.put("jpeg", MediaType.IMAGE_JPEG);
        map.put("png", MediaType.IMAGE_PNG);
        map.put("multipart", MediaType.MULTIPART_FORM_DATA);
        map.put("html", MediaType.TEXT_PLAIN);
        map.put("js", MediaType.TEXT_PLAIN);
        map.put("plain", MediaType.TEXT_PLAIN);
        map.put("markdown", MediaType.TEXT_MARKDOWN);

        configurer.ignoreAcceptHeader(false)
                .favorPathExtension(true)
                .favorParameter(false)
                .defaultContentType(MediaType.APPLICATION_JSON_UTF8)
                .mediaTypes(map);
    }

    @Bean
    ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }
}
