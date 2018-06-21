package com.agileframework.agileclient.common.properties;

/**
 * Created by 佟盟 on 2018/1/11
 */
public class DruidConfigProperty {
    private static String type = "MYSQL";
    private static String dataBaseName = "agile_db";
    private static String dataBaseIp = "127.0.0.1";
    private static String dataBasePost = "3306";
    private static String dataBaseUsername = "root";
    private static String dataBasePassword = "123456";
    private static String dataBaseUrlParam = "serverTimezone=GMT%2B8&useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static int initSize = 1;
    private static int minIdle = 1;
    private static int maxActive = 100;
    private static int maxWait = 60000;
    private static boolean removeAbandoned = true;
    private static int removeAbandonedTimeout = 300000;
    private static int timeBetweenEvictionRunsMillis = 60000;
    private static int minEvictableIdleTimeMillis = 300000;
    private static String validationQuery = "SELECT 1";
    private static boolean testWhileIdle = true;
    private static boolean testOnBorrow = false;
    private static boolean testOnReturn = false;
    private static boolean poolPreparedStatements = true;
    private static int maxPoolPreparedStatementPerConnectionSize = 20;
    private static String filters = "stat,wall";
    private static boolean globalDataSourceStat = true;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getDataBaseIp() {
        return dataBaseIp;
    }

    public void setDataBaseIp(String dataBaseIp) {
        this.dataBaseIp = dataBaseIp;
    }

    public String getDataBasePost() {
        return dataBasePost;
    }

    public void setDataBasePost(String dataBasePost) {
        this.dataBasePost = dataBasePost;
    }

    public String getDataBaseUsername() {
        return dataBaseUsername;
    }

    public void setDataBaseUsername(String dataBaseUsername) {
        this.dataBaseUsername = dataBaseUsername;
    }

    public String getDataBasePassword() {
        return dataBasePassword;
    }

    public void setDataBasePassword(String dataBasePassword) {
        this.dataBasePassword = dataBasePassword;
    }

    public String getDataBaseUrlParam() {
        return dataBaseUrlParam;
    }

    public void setDataBaseUrlParam(String dataBaseUrlParam) {
        this.dataBaseUrlParam = dataBaseUrlParam;
    }

    public int getInitSize() {
        return initSize;
    }

    public void setInitSize(int initSize) {
        this.initSize = initSize;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public boolean isRemoveAbandoned() {
        return removeAbandoned;
    }

    public void setRemoveAbandoned(boolean removeAbandoned) {
        this.removeAbandoned = removeAbandoned;
    }

    public int getRemoveAbandonedTimeout() {
        return removeAbandonedTimeout;
    }

    public void setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
        this.removeAbandonedTimeout = removeAbandonedTimeout;
    }

    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public int getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public boolean isGlobalDataSourceStat() {
        return globalDataSourceStat;
    }

    public void setGlobalDataSourceStat(boolean globalDataSourceStat) {
        this.globalDataSourceStat = globalDataSourceStat;
    }
}
