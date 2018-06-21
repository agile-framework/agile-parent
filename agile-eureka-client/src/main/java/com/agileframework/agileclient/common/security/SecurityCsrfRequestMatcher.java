package com.agileframework.agileclient.common.security;

import com.agileframework.agileclient.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mydeathtrial on 2017/3/8
 */
@Component
public class SecurityCsrfRequestMatcher implements RequestMatcher {

    /**
     * 需要排除的请求类型
     */
    @Value("${agile.security.csrf_allow_methods}")
    private String allowedMethod;

    /**
     * 需要排除的url列表
     */
    @Value("${agile.security.csrf_allow_url}")
    private String allowedUrl;

    @Override
    public boolean matches(HttpServletRequest httpServletRequest) {
        if (!StringUtil.isEmpty(allowedUrl)) {
            String servletPath = httpServletRequest.getServletPath();
            String[] allowedUrls = allowedUrl.split(",");
            for (int i = 0 ; i < allowedUrls.length ; i++) {
                if (servletPath.contains(allowedUrls[i])) {
                    return false;
                }
            }
        }
        if (!StringUtil.isEmpty(allowedMethod)) {
            String servletMethod = httpServletRequest.getMethod();
            String[] allowedMethods = allowedMethod.split(",");
            for (int i = 0 ; i < allowedMethods.length ; i++) {
                if (servletMethod.contains(allowedMethods[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setAllowedUrl(String allowedUrl) {
        this.allowedUrl = allowedUrl;
    }

    public void setAllowedMethod(String allowedMethod) {
        this.allowedMethod = allowedMethod;
    }

}
