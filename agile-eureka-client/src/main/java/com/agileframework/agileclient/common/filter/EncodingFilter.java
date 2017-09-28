package com.agileframework.agileclient.common.filter;

import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by 佟盟 on 2017/9/25
 */
@WebFilter(filterName = "SpringEncodingFilter",urlPatterns = "/*",initParams = {
        @WebInitParam(name = "encoding",value = "UTF-8"),@WebInitParam(name = "forceEncoding",value = "true")
})
public class EncodingFilter extends CharacterEncodingFilter {
}
