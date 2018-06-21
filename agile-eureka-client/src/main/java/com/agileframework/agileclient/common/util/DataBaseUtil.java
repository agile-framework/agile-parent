package com.agileframework.agileclient.common.util;

import com.agileframework.agileclient.common.exception.NonSupportDBException;

import java.sql.*;


/**
 * Created by mydeathtrial on 2017/3/10
 */
public class DataBaseUtil {
    public static String type;
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
        //加载数据库驱动类
        StringBuilder druidUrl = new StringBuilder();
        String db = PropertiesUtil.getProperty("agile.jpa.db").toLowerCase();
        switch (db){
            case "mysql":
                type = "mysql";
                Class.forName("com.mysql.cj.jdbc.Driver");
                druidUrl.append("jdbc:mysql://").append(PropertiesUtil.getProperty("agile.druid.data_base_ip")).append(":").append(PropertiesUtil.getProperty("agile.druid.data_base_post")).append("/").append(PropertiesUtil.getProperty("agile.druid.data_base_name")).append("?").append(PropertiesUtil.getProperty("agile.druid.data_base_url_param"));
                break;
            case "oracle":
                type = "oracle";
                Class.forName("oracle.jdbc.driver.OracleDriver");
                druidUrl.append("jdbc:oracle:thin:@").append(PropertiesUtil.getProperty("agile.druid.data_base_ip")).append(":").append(PropertiesUtil.getProperty("agile.druid.data_base_post")).append(":").append(PropertiesUtil.getProperty("agile.druid.data_base_name"));
                break;
            default:
                try {
                    throw new NonSupportDBException();
                } catch (NonSupportDBException e) {
                    e.printStackTrace();
                }
        }

        //建立数据库连接
        if (ObjectUtil.isEmpty(connection)){
            connection = DriverManager.getConnection(druidUrl.toString(),PropertiesUtil.getProperty("agile.druid.data_base_username"), PropertiesUtil.getProperty("agile.druid.data_base_password"));
        }

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