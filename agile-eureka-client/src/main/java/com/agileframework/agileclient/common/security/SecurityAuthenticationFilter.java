package com.agileframework.agileclient.common.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 佟盟 on 2017/1/13
 */
public class SecurityAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String USERNAME = "agile_username";
    private static final String PASSWORD = "agile_password";

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
        //获取用户名密码
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        //判断用户信息
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        //判断会话存在
        HttpSession session = request.getSession(false);
        //如果session不为空，添加username到session中
        if (session != null || getAllowSessionCreation()) {
            request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME_KEY", TextEscapeUtils.escapeEntities(username));
        }
        //允许子类设置详细属性
        setDetails(request, authRequest);

        //运行UserDetailsService的loadUserByUsername 再次封装Authentication
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 获取用户名
     * @param request 请求对象
     * @return 用户名
     */
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        Object obj = request.getParameter(USERNAME);
        return null == obj ? "" : obj.toString();
    }

    /**
     * 获取密码
     * @param request 请求对象
     * @return 用户名
     */
    @Override
    protected String obtainPassword(HttpServletRequest request) {
        Object obj = request.getParameter(PASSWORD);
        return null == obj ? "" : obj.toString();
    }
}
