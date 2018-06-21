package com.agileframework.agileclient.common.configure;

import com.agileframework.agileclient.common.exception.NonSupportDBException;
import com.agileframework.agileclient.common.properties.CacheConfigProperties;
import com.agileframework.agileclient.common.properties.DBConfigProperties;
import com.agileframework.agileclient.common.properties.DruidConfigProperty;
import com.agileframework.agileclient.common.properties.JPAConfigProperty;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Properties;


/**
 * Created by 佟盟 on 2017/10/7
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = {"com.agileframework.agileclient.mvc.model.dao"},transactionManagerRef = "transactionManager",entityManagerFactoryRef = "entityManagerFactory")
public class JPAConfig {
    private static int index = 0;

    private DruidConfigProperty druidConfigProperty;
    private JPAConfigProperty jpaConfigProperty;

    @PostConstruct
    private void init(){
        this.druidConfigProperty = DBConfigProperties.getDruid().get(index);
        this.jpaConfigProperty = DBConfigProperties.getJpa().get(index);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.valueOf(druidConfigProperty.getType()));
        jpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(jpaConfigProperty.getGenerateDdl()));
        String db = druidConfigProperty.getType().toLowerCase();
        switch (db){
            case "mysql":
                jpaVendorAdapter.setDatabasePlatform( "org.hibernate.dialect.MySQLDialect");
                break;
            case "oracle":
                jpaVendorAdapter.setDatabasePlatform( "org.hibernate.dialect.Oracle12cDialect");
                break;
            default:
                try {
                    throw new NonSupportDBException();
                } catch (NonSupportDBException e) {
                    e.printStackTrace();
                }
        }
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("com.agileframework.agileclient.mvc.model.entity");
        entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setJpaProperties(jpaProperties());
        return entityManagerFactory;
    }

    private Properties jpaProperties(){
        String cacheProxy = CacheConfigProperties.getProxy().toLowerCase();

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto",jpaConfigProperty.getHbm2ddl());
        properties.setProperty("hibernate.current_session_context_class",jpaConfigProperty.getCurrentSessionContextClass());
        properties.setProperty("hibernate.cache.use_second_level_cache",jpaConfigProperty.getUseSecondLevelCache());
        switch (cacheProxy){
            case "redis":
                properties.setProperty("hibernate.cache.region.factory_class","com.agileframework.agileclient.common.cache.redis.RedisRegionFactory");
                break;
            case "ehcache":
                properties.setProperty("hibernate.cache.region.factory_class","com.agileframework.agileclient.common.cache.ehCache.EhCacheRegionFactory");
                break;
        }
        properties.setProperty("hibernate.cache.use_structured_entries",jpaConfigProperty.getUseStructuredEntries());
        properties.setProperty("hibernate.cache.use_query_cache",jpaConfigProperty.getUseQueryCache());
        properties.setProperty("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
        properties.setProperty("hibernate.show_sql",jpaConfigProperty.getShowSql());
        properties.setProperty("hibernate.format_sql",jpaConfigProperty.getFormatSql());
        properties.setProperty("hibernate.ejb.interceptor","com.agileframework.agileclient.common.interceptor.JpaInterceptor");
        properties.setProperty("hibernate.cache.region_prefix","hibernate");
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(dataSource).getObject());
        return transactionManager;
    }

}
