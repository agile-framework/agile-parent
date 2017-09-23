package com.agileframework.agileclient;

import com.agileframework.agileclient.common.annotation.ExcludeComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
//开启Eureka客户端
@EnableEurekaClient
//开启配置注解
@Configuration
//开启自动识别注解
@ComponentScan
//开启自动配置
@EnableAutoConfiguration
//开启自定义系统属性配置
@EnableConfigurationProperties({JpaProperties.class})
//排除加载范围
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeComponentScan.class)})
//扫面servlet注解
@ServletComponentScan
//开启缓存功能
@EnableCaching
//开启feign组件
@EnableFeignClients
//开启Hystirx
@EnableCircuitBreaker
//开启刷新功能
@RefreshScope
//开启云服务调度
//@EnableTask
public class AgileClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(AgileClientApplication.class, args);
	}

	//    @GetMapping("/welcome")
//    public String welcome() {
//        return "welcome";
//    }
//
//    @RequestMapping("/user")
//    public Principal user(Principal user) {
//        return user;
//    }
//
//    @Bean
//    public RequestContextListener requestContextListener() {
//        return new RequestContextListener();
//    }
//
//    @Component
//    @EnableOAuth2Sso // 实现基于OAuth2的单点登录，建议跟踪进代码阅读以下该注解的注释，很有用
//    public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http.
//                    antMatcher("/**")
//                    // 所有请求都得经过认证和授权
//                    .authorizeRequests().anyRequest().authenticated()
//                    .and().authorizeRequests().antMatchers("/","/anon").permitAll()
//                    .and()
//                    // 这里之所以要禁用csrf，是为了方便。
//                    // 否则，退出链接必须要发送一个post请求，请求还得带csrf token
//                    // 那样我还得写一个界面，发送post请求
//                    .csrf().disable()
//                    // 退出的URL是/logout
//                    .logout().logoutUrl("/logout").permitAll()
//                    // 退出成功后，跳转到/路径。
//                    .logoutSuccessUrl("/");
//        }
//    }
}
