package com.agileframework.agileclient.common.configure;

import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Created by 佟盟 on 2017/8/22
 */
@Component
public class JsonView  extends MappingJackson2JsonView {

    public JsonView() {
        Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean = new Jackson2ObjectMapperFactoryBean();
        jackson2ObjectMapperFactoryBean.setIndentOutput(true);
        jackson2ObjectMapperFactoryBean.setSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setPrettyPrint(true);
    }
}
