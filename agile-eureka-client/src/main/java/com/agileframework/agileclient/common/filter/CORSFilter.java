package com.agileframework.agileclient.common.filter;

import com.agileframework.agileclient.common.util.StringUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 佟盟 on 2017/9/25
 */
@WebFilter(filterName = "CORSFilter",urlPatterns = "/*",initParams = {
        @WebInitParam(name = "allowOrigin",value = "www.agileframework.com"),@WebInitParam(name = "allowMethods",value = "GET,POST,PUT,DELETE,OPTIONS"),@WebInitParam(name = "allowCredentials",value = "true"),@WebInitParam(name = "allowHeaders",value = "Content-Type")
})
public class CORSFilter implements Filter {
    private String allowOrigin;
    private String allowMethods;
    private String allowCredentials;
    private String allowHeaders;
    private String exposeHeaders;

    @Override
    public void init(FilterConfig filterConfig) {
        allowOrigin = filterConfig.getInitParameter("allowOrigin");
        allowMethods = filterConfig.getInitParameter("allowMethods");
        allowCredentials = filterConfig.getInitParameter("allowCredentials");
        allowHeaders = filterConfig.getInitParameter("allowHeaders");
        exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (allowOrigin.equals("*") || StringUtil.isEmpty(allowOrigin)){
            response.setHeader("Access-Control-Allow-Origin", allowOrigin);
        }else{
            List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
            String currentOrigin = request.getHeader("Origin");
            if (allowOriginList.contains(currentOrigin)) {
                response.setHeader("Access-Control-Allow-Origin", currentOrigin);
            }
        }
        if (StringUtil.isNotEmpty(allowOrigin)) {
            response.setHeader("Access-Control-Allow-Origin", allowOrigin);
        }
        if (StringUtil.isNotEmpty(allowMethods)) {
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (StringUtil.isNotEmpty(allowCredentials)) {
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }
        if (StringUtil.isNotEmpty(allowHeaders)) {
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }
        if (StringUtil.isNotEmpty(exposeHeaders)) {
            response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}
