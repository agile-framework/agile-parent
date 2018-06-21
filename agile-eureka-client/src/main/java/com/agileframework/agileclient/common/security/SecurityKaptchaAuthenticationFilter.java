package com.agileframework.agileclient.common.security;

import com.google.code.kaptcha.Constants;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆验证码拦截器
 * Created by 佟盟 on 2017/9/27
 */
public class SecurityKaptchaAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private String servletPath;
    private String parameter;
    public SecurityKaptchaAuthenticationFilter(String servletPath,String failureUrl,String parameter) {
        super(servletPath);
        this.servletPath = servletPath;
        this.parameter = parameter;
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));

    }

    @Override

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse)response;
        if ("POST".equalsIgnoreCase(req.getMethod())&&servletPath.equals(req.getServletPath())){
            String expect = (String) req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if(expect!=null&&!expect.equalsIgnoreCase(req.getParameter(parameter))){
                unsuccessfulAuthentication(req, res, new InsufficientAuthenticationException("输入的验证码不正确"));
                return;
            }
        }
        chain.doFilter(request,response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return null;

    }
}
