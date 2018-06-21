package com.agileframework.agileclient.common.configure;

import com.agileframework.agileclient.common.exception.NonSupportDBException;
import com.agileframework.agileclient.common.properties.DBConfigProperties;
import com.agileframework.agileclient.common.properties.DruidConfigProperty;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

/**
 * Created by 佟盟 on 2017/10/7
 */
@Configuration
public class DruidConfig {
    private static int index = 0;

    private DruidConfigProperty druidConfigProperty;

    @PostConstruct
    private void init(){
        this.druidConfigProperty = DBConfigProperties.getDruid().get(index);
    }

    @Bean(initMethod = "init",destroyMethod = "close")
    DruidDataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();

        StringBuilder druidUrl = new StringBuilder();
        String db = druidConfigProperty.getType().toLowerCase();
        switch (db){
            case "mysql":
                druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                druidUrl.append("jdbc:mysql://").append(druidConfigProperty.getDataBaseIp()).append(":").append(druidConfigProperty.getDataBasePost()).append("/").append(druidConfigProperty.getDataBaseName()).append("?").append(druidConfigProperty.getDataBaseUrlParam());
                break;
            case "oracle":
                druidDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
                druidUrl.append("jdbc:oracle:thin:@").append(druidConfigProperty.getDataBaseIp()).append(":").append(druidConfigProperty.getDataBasePost()).append(":").append(druidConfigProperty.getDataBaseName());
                break;
            default:
                try {
                    throw new NonSupportDBException();
                } catch (NonSupportDBException e) {
                    e.printStackTrace();
                }
        }

        druidDataSource.setUrl(druidUrl.toString());
        druidDataSource.setUsername(druidConfigProperty.getDataBaseUsername());
        druidDataSource.setPassword(druidConfigProperty.getDataBasePassword());
        druidDataSource.setInitialSize(druidConfigProperty.getInitSize());
        druidDataSource.setMinIdle(druidConfigProperty.getMinIdle());
        druidDataSource.setMaxActive(druidConfigProperty.getMaxActive());
        druidDataSource.setMaxWait(druidConfigProperty.getMaxWait());
        druidDataSource.setRemoveAbandoned(druidConfigProperty.isRemoveAbandoned());
        druidDataSource.setRemoveAbandonedTimeout(druidConfigProperty.getRemoveAbandonedTimeout());
        druidDataSource.setTimeBetweenEvictionRunsMillis(druidConfigProperty.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(druidConfigProperty.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(druidConfigProperty.getValidationQuery());
        druidDataSource.setTestWhileIdle(druidConfigProperty.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(druidConfigProperty.isTestOnBorrow());
        druidDataSource.setTestOnReturn(druidConfigProperty.isTestOnReturn());
        druidDataSource.setFilters(druidConfigProperty.getFilters());
        druidDataSource.setPoolPreparedStatements(druidConfigProperty.isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidConfigProperty.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setUseGlobalDataSourceStat(druidConfigProperty.isGlobalDataSourceStat());
        return druidDataSource;

    }
}
