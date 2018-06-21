package com.agileframework.agileclient.common.configure;

import com.agileframework.agileclient.common.properties.SecurityProperties;
import com.agileframework.agileclient.common.security.SecurityAuthenticationFilter;
import com.agileframework.agileclient.common.security.SecurityAuthenticationProvider;
import com.agileframework.agileclient.common.security.SecurityKaptchaAuthenticationFilter;
import com.agileframework.agileclient.common.security.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Collections;

/**
 * Created by 佟盟 on 2017/9/26
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final RequestMatcher securityCsrfRequestMatcher;
    private final AuthenticationProvider securityAuthenticationProvider;
    private final SecurityUserDetailsService securityUserDetailsService;

    @Autowired
    public SecurityConfig(RequestMatcher securityCsrfRequestMatcher, SecurityAuthenticationProvider securityAuthenticationProvider, SecurityUserDetailsService securityUserDetailsService) {
        this.securityCsrfRequestMatcher = securityCsrfRequestMatcher;
        this.securityAuthenticationProvider = securityAuthenticationProvider;
        this.securityUserDetailsService = securityUserDetailsService;
    }

    //http://localhost:8080/login 输入正确的用户名密码 并且选中remember-me 则登陆成功，转到 index页面
    //再次访问index页面无需登录直接访问
    //访问http://localhost:8080/home 不拦截，直接访问，
    //访问http://localhost:8080/hello 需要登录验证后，且具备 “ADMIN”权限hasAuthority("ADMIN")才可以访问
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().authenticationEntryPoint(loginUrlAuthenticationEntryPoint())
                .and()
                .authorizeRequests().antMatchers(SecurityProperties.getNotLoginUrl(),SecurityProperties.getInvalidSessionUrl(),"/druid/**","/verification","/actuator/**","/actuator").permitAll()//访问：无需登录认证权限
                .and()
                .authorizeRequests().antMatchers("/**").access("isAuthenticated()")
//                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
//                .antMatchers("/druid/*").hasAuthority("ADMIN") //登陆后之后拥有“ADMIN”权限才可以访问/hello方法，否则系统会出现“403”权限不足的提示
                .and()
                    .exceptionHandling()
                .and()
                    .addFilterBefore(new SecurityKaptchaAuthenticationFilter(SecurityProperties.getLoginUrl(), SecurityProperties.getFailureUrl(),SecurityProperties.getVerificationCode()), UsernamePasswordAuthenticationFilter.class)//验证码
                    .csrf()
                    .requireCsrfProtectionMatcher(securityCsrfRequestMatcher)//CSRF
                .and()
                    .addFilterAt(securityAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .rememberMe().key("e37f4b31-0c45-11dd-bd0b-0800200c9a66").tokenValiditySeconds(1209600)
                .and()
                    .logout()
                        .logoutUrl(SecurityProperties.getLoginOutUrl())
                        .logoutSuccessUrl(SecurityProperties.getLoginOutSuccessUrl())
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(simpleUrlLogoutSuccessHandler())
                        .permitAll()//退出
                .and().exceptionHandling().accessDeniedPage(SecurityProperties.getAccessDenied())//权限不足跳页
                .and()
                   .sessionManagement()
                        .invalidSessionUrl(SecurityProperties.getInvalidSessionUrl()).maximumSessions(1).expiredUrl(SecurityProperties.getExpiredSessionUrl()).sessionRegistry(sessionRegistry())//Session

        ;
    }



    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers(SecurityProperties.getNotLoginUrl(), SecurityProperties.getInvalidSessionUrl(),"/druid/**", "/swagger**","/verification");
    }

    @Override
    protected AuthenticationManager authenticationManager(){
        ProviderManager authenticationManager = new ProviderManager(Collections.singletonList(securityAuthenticationProvider));
        //不擦除认证密码，擦除会导致TokenBasedRememberMeServices因为找不到Credentials再调用UserDetailsService而抛出UsernameNotFoundException
        authenticationManager.setEraseCredentialsAfterAuthentication(false);
        return authenticationManager;
    }

    @Bean
    LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint(){
        return new LoginUrlAuthenticationEntryPoint(SecurityProperties.getNotLoginUrl());
    }

    @Bean
    SecurityAuthenticationFilter securityAuthenticationFilter() throws Exception {
        SecurityAuthenticationFilter filter = new SecurityAuthenticationFilter();
        //切入登陆过滤链路地址
        filter.setFilterProcessesUrl(SecurityProperties.getLoginUrl());

        //成功处理链路
        savedRequestAwareAuthenticationSuccessHandler().setAlwaysUseDefaultTargetUrl(true);
        savedRequestAwareAuthenticationSuccessHandler().setDefaultTargetUrl(SecurityProperties.getSuccessUrl());
        filter.setAuthenticationSuccessHandler(savedRequestAwareAuthenticationSuccessHandler());

        //失败处理链路
        simpleUrlAuthenticationFailureHandler().setDefaultFailureUrl(SecurityProperties.getFailureUrl());
        filter.setAuthenticationFailureHandler(simpleUrlAuthenticationFailureHandler());

        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler(){
        return new SavedRequestAwareAuthenticationSuccessHandler();
    }

    @Bean
    SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler(){
        return new SimpleUrlAuthenticationFailureHandler();
    }

    @Bean
    SimpleUrlLogoutSuccessHandler simpleUrlLogoutSuccessHandler(){
        return new SimpleUrlLogoutSuccessHandler();
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("tongmeng").password("tongmeng").roles("ADMIN");
        auth.userDetailsService(securityUserDetailsService).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:org/springframework/security/messages_zh_CN");
        messageSource.setDefaultEncoding("utf-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setCacheSeconds(10);
        return messageSource;
    }

}
