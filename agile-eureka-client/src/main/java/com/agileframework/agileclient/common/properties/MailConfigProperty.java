package com.agileframework.agileclient.common.properties;

/**
 * Created by 佟盟 on 2018/4/4
 */
public class MailConfigProperty {
    private static String serverHost = "smtp.163.com";
    private static int serverPort = 25;
    private static String serverDefaultFrom = "mydeathtrial@163.com";
    private static String serverUsername = "mydeathtrial@163.com";
    private static String serverPassword = "tongmeng19900905";

    public static String getServerHost() {
        return serverHost;
    }

    public static void setServerHost(String serverHost) {
        MailConfigProperty.serverHost = serverHost;
    }

    public static int getServerPort() {
        return serverPort;
    }

    public static void setServerPort(int serverPort) {
        MailConfigProperty.serverPort = serverPort;
    }

    public static String getServerDefaultFrom() {
        return serverDefaultFrom;
    }

    public static void setServerDefaultFrom(String serverDefaultFrom) {
        MailConfigProperty.serverDefaultFrom = serverDefaultFrom;
    }

    public static String getServerUsername() {
        return serverUsername;
    }

    public static void setServerUsername(String serverUsername) {
        MailConfigProperty.serverUsername = serverUsername;
    }

    public static String getServerPassword() {
        return serverPassword;
    }

    public static void setServerPassword(String serverPassword) {
        MailConfigProperty.serverPassword = serverPassword;
    }
}
