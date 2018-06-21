package com.agileframework.agileclient.common.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.text.SimpleDateFormat;

/**
 * Created by 佟盟 on 2017/8/1
 */
@Component
public class JsonView extends MappingJackson2JsonView {
    public JsonView() {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = jackson2HttpMessageConverter.getObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        this.setPrettyPrint(true);
        this.setObjectMapper(objectMapper);
        this.setBeanName("jsonView");
    }
}
