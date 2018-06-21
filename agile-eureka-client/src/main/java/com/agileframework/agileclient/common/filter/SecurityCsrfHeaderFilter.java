package com.agileframework.agileclient.common.filter;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 佟盟 on 2017/3/7
 */
public class SecurityCsrfHeaderFilter extends OncePerRequestFilter implements Filter {
    private String tokenName;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        CsrfToken csrf = (CsrfToken) httpServletRequest.getAttribute(CsrfToken.class.getName());
        if (csrf != null) {
            Cookie cookie = WebUtils.getCookie(httpServletRequest, tokenName);
            String token = csrf.getToken();
            if (cookie==null || token!=null && !token.equals(cookie.getValue())) {
                cookie = new Cookie(tokenName, token);
                cookie.setPath("/");
                httpServletResponse.addCookie(cookie);
            }
        }else{
            Cookie cookie = new Cookie(tokenName, null);
            cookie.setMaxAge(0);
            httpServletResponse.addCookie(cookie);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
}
