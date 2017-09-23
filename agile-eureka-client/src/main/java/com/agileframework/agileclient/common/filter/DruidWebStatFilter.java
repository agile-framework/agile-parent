package com.agileframework.agileclient.common.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Druid监控过滤器
 * Created by 佟盟 on 2017/8/27
 */
@WebFilter(filterName = "DruidWebStatFilter",urlPatterns = "/*",initParams = {
        @WebInitParam(name = "exclusions",value = "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"),@WebInitParam(name = "sessionStatMaxCount",value = "100000")
})
public class DruidWebStatFilter extends WebStatFilter {
}
