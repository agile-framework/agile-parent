package com.agileframework.agileclient.common.container;

import com.agileframework.agileclient.common.configure.LoggerFactoryConfig;
import com.agileframework.agileclient.common.filter.CORSFilter;
import com.agileframework.agileclient.common.filter.SecurityCsrfHeaderFilter;
import com.agileframework.agileclient.common.listener.CacheListener;
import com.agileframework.agileclient.common.util.ObjectUtil;
import com.agileframework.agileclient.common.util.PropertiesUtil;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Created by 佟盟 on 2017/9/27
 */
public class WebInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        /*
          优先启动log4j2配置
         */
        ConfigurationFactory.setConfigurationFactory(LoggerFactoryConfig.getInstance());
        /*
          CORS过滤器
         */
        FilterRegistration.Dynamic corsFilter = servletContext.addFilter("CORSFilter", CORSFilter.class);
        corsFilter.setInitParameter("allowOrigin",PropertiesUtil.getProperty("agile.servlet.allow_origin",String.class));
        corsFilter.setInitParameter("allowMethods",PropertiesUtil.getProperty("agile.servlet.allow_methods",String.class));
        corsFilter.setInitParameter("allowCredentials",PropertiesUtil.getProperty("agile.servlet.allow_credentials",String.class));
        corsFilter.setInitParameter("allowHeaders",PropertiesUtil.getProperty("agile.servlet.allow_headers",String.class));
        corsFilter.addMappingForUrlPatterns(null, false, "/*");

        /*
          编码过滤器
         */
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("EncodingFilter", CharacterEncodingFilter.class);
        encodingFilter.setInitParameter("encoding", PropertiesUtil.getProperty("agile.servlet.character",String.class));
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.setAsyncSupported(true);
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");

        /*
          Druid监控过滤器
         */
        FilterRegistration.Dynamic druidWebStatFilter = servletContext.addFilter("DruidWebStatFilter", WebStatFilter.class);
        druidWebStatFilter.setInitParameter("exclusions",PropertiesUtil.getProperty("agile.druid.exclusions",String.class));
        druidWebStatFilter.setInitParameter("sessionStatMaxCount",PropertiesUtil.getProperty("agile.druid.session_stat_max_count",String.class));
        druidWebStatFilter.addMappingForUrlPatterns(null, false, "/*");

        /*
          Security过滤器
         */
        FilterRegistration.Dynamic securityFilter = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
        if(!ObjectUtil.isEmpty(securityFilter))
        securityFilter.addMappingForUrlPatterns(null, false, "/*");

        /*
          CSRF过滤器
         */
        FilterRegistration.Dynamic agileCsrfHeaderFilter = servletContext.addFilter("agileCsrfHeaderFilter", SecurityCsrfHeaderFilter.class);
        agileCsrfHeaderFilter.setInitParameter("tokenName",PropertiesUtil.getProperty("agile.security.csrf_token_name",String.class));
        agileCsrfHeaderFilter.addMappingForUrlPatterns(null, false, "/*");

        /*
          验证码Servlet
         */
        ServletRegistration.Dynamic kaptchaServlet = servletContext.addServlet("VerificationCodeServlet", KaptchaServlet.class);
        kaptchaServlet.addMapping("/"+PropertiesUtil.getProperty("agile.kaptcha.url"));
        ;

        /*
          DruidServlet
         */
        ServletRegistration.Dynamic druidStatViewServlet = servletContext.addServlet("DruidStatViewServlet", StatViewServlet.class);
        druidStatViewServlet.setInitParameter("resetEnable",PropertiesUtil.getProperty("agile.druid.reset_enable",String.class));
        druidStatViewServlet.setInitParameter("loginUsername",PropertiesUtil.getProperty("agile.druid.manager_name",String.class));
        druidStatViewServlet.setInitParameter("loginPassword",PropertiesUtil.getProperty("agile.druid.manager_password",String.class));
        druidStatViewServlet.addMapping("/druid/*");

        /*
          SpringDispatcherServlet
         */
        ServletRegistration.Dynamic springDispatcherServlet = servletContext.addServlet("SpringDispatcherServlet", DispatcherServlet.class);
        springDispatcherServlet.setInitParameter("contextClass","org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
        springDispatcherServlet.setInitParameter("contextConfigLocation","com.agile.common.config.SpringConfig");
        springDispatcherServlet.setInitParameter("dispatchOptionsRequest","true");
        springDispatcherServlet.setLoadOnStartup(1);
        springDispatcherServlet.addMapping("/*");

        /*
          内存溢出监听
         */
        servletContext.addListener(IntrospectorCleanupListener.class);

        /*
          初始化缓存监听
         */
        servletContext.addListener(CacheListener.class);

        /*
          session监听
         */
        servletContext.addListener(HttpSessionEventPublisher.class);

        /*
          项目根目录监听
         */
        servletContext.addListener(WebAppRootListener.class);
    }


}
