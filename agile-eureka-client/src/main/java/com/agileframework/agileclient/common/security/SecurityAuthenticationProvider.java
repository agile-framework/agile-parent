package com.agileframework.agileclient.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by 佟盟 on 2017/1/13
 */
@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private SecurityUserDetailsService securityUserDetailsService;

    @Autowired
    public SecurityAuthenticationProvider(SecurityUserDetailsService securityUserDetailsService) {
        super();
        this.securityUserDetailsService = securityUserDetailsService;
    }

    /**
     * 登录验证
     */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        //从数据库找到的用户
        UserDetails userDetails = null;
        if(username != null) {
            userDetails = securityUserDetailsService.loadUserByUsername(username);
        }
        //
//        if(userDetails == null) {
//            throw new UsernameNotFoundException("用户名/密码无效");
//        }else if (!userDetails.isEnabled()){
//            throw new DisabledException("用户已被禁用");
//        }else if (!userDetails.isAccountNonExpired()) {
//            throw new AccountExpiredException("账号已过期");
//        }else if (!userDetails.isAccountNonLocked()) {
//            throw new LockedException("账号已被锁定");
//        }else if (!userDetails.isCredentialsNonExpired()) {
//            throw new LockedException("凭证已过期");
//        }
//
//        //数据库用户的密码
//        String password = userDetails.getPassword();
//
//        //密码校验
//        if(!password.equals(token.getCredentials())) {
//            throw new BadCredentialsException("用户名密码验证失败");
//        }
        //授权
        return new UsernamePasswordAuthenticationToken(userDetails, 12,userDetails.getAuthorities());
    }

    public boolean supports(Class<?> authentication) {
        //返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}