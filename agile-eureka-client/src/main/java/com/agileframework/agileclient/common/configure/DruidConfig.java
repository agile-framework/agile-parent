package com.agileframework.agileclient.common.configure;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据连接池配置
 * Created by 佟盟 on 2017/8/27
 */
@Configuration
public class DruidConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "agile.druid")
    public DataSource dataSource() {
        return new DruidDataSource();
    }
}
