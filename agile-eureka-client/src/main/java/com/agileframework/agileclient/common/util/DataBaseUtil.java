package com.agileframework.agileclient.common.util;

import java.sql.*;


/**
 * Created by mydeathtrial on 2017/3/10
 */
public class DataBaseUtil {
    public static ResultSet resultSet;
    public static DatabaseMetaData databaseMetaData;
    public static Statement statement;
    public static Connection connection;

    /**
     * 初始化数据库连接
     *
     * @throws ClassNotFoundException 类找不到
     * @throws SQLException           SQL异常
     */
    public static void initDB() throws ClassNotFoundException, SQLException {
        PropertiesUtil propertiesUtil = new PropertiesUtil("./src/main/resources/com/agile/configure/agile.properties");

        //加载数据库驱动类
        Class.forName(propertiesUtil.getProperty("agile.druid.driver_class_name"));

        //建立数据库连接
        if (ObjectUtil.isEmpty(connection))
            connection = DriverManager.getConnection(propertiesUtil.getProperty("agile.druid.jdbc_url_prefix") + propertiesUtil.getProperty("agile.druid.data_base_ip") + ":" + propertiesUtil.getProperty("agile.druid.data_base_post") + "/" + propertiesUtil.getProperty("agile.druid.data_base_name") + "?" + propertiesUtil.getProperty("agile.druid.data_base_url_param"), propertiesUtil.getProperty("agile.druid.data_base_username"), propertiesUtil.getProperty("agile.druid.data_base_password"));

        //数据库信息
        if (ObjectUtil.isEmpty(databaseMetaData)) databaseMetaData = connection.getMetaData();

        //操作数据库对象
        if (ObjectUtil.isEmpty(statement)) statement = connection.createStatement();
    }

    /**
     * 数据库查询
     *
     * @param sql SQL语句
     * @return 结果集
     * @throws SQLException SQL异常
     */
    public static ResultSet excuteSQL(String sql) throws SQLException {
        return resultSet = statement.executeQuery(sql);
    }

    /**
     * 注销数据库连接
     *
     * @throws SQLException SQL异常
     */
    public static void destroyDB() throws SQLException {

        if (!ObjectUtil.isEmpty(resultSet)) resultSet.close();

        if (!ObjectUtil.isEmpty(statement)) statement.close();

        if (!ObjectUtil.isEmpty(connection)) connection.close();
    }
}