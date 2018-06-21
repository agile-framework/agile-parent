package com.agileframework.agileclient.common.filter;

import com.agileframework.agileclient.common.util.StringUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 佟盟 on 2017/9/25
 */
public class CORSFilter extends OncePerRequestFilter implements Filter {
    private String allowOrigin;
    private String allowMethods;
    private String allowCredentials;
    private String allowHeaders;
    private String exposeHeaders;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (allowOrigin.equals("*") || StringUtil.isEmpty(allowOrigin)){
            httpServletResponse.setHeader("Access-Control-Allow-Origin", allowOrigin);
        }else{
            List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
            String currentOrigin = httpServletRequest.getHeader("Origin");
            if (allowOriginList.contains(currentOrigin)) {
                httpServletResponse.setHeader("Access-Control-Allow-Origin", currentOrigin);
            }
        }
        if (StringUtil.isNotEmpty(allowOrigin)) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin", allowOrigin);
        }
        if (StringUtil.isNotEmpty(allowMethods)) {
            httpServletResponse.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (StringUtil.isNotEmpty(allowCredentials)) {
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }
        if (StringUtil.isNotEmpty(allowHeaders)) {
            httpServletResponse.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }
        if (StringUtil.isNotEmpty(exposeHeaders)) {
            httpServletResponse.setHeader("Access-Control-Expose-Headers", exposeHeaders);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    public void setAllowOrigin(String allowOrigin) {
        this.allowOrigin = allowOrigin;
    }

    public void setAllowMethods(String allowMethods) {
        this.allowMethods = allowMethods;
    }

    public void setAllowCredentials(String allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    public void setAllowHeaders(String allowHeaders) {
        this.allowHeaders = allowHeaders;
    }

    public void setExposeHeaders(String exposeHeaders) {
        this.exposeHeaders = exposeHeaders;
    }
}
