package com.agileframework.agileclient.common.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Created by 佟盟 on 2017/8/28
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef="entityManagerFactory",transactionManagerRef="transactionManager",basePackages= {"com.agileframework.agileclient.mvc.model.dao"})
public class JpaConfig {

    private final DataSource dataSource;

    private final JpaProperties jpaProperties;

    @Autowired
    public JpaConfig(JpaProperties jpaProperties, DataSource dataSource) {
        this.jpaProperties = jpaProperties;
        this.dataSource = dataSource;
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(EntityManagerFactoryBuilder builder){
        return builder.dataSource(dataSource)
                .properties(jpaProperties.getHibernateProperties(dataSource))
                .packages(jpaProperties.getProperties().get("entity-packages"))
                .persistenceUnit(jpaProperties.getProperties().get("persistence-unit")).build();

    }

    @Bean(name = "transactionManager")
    @Primary
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean(builder).getObject()));
    }
}
