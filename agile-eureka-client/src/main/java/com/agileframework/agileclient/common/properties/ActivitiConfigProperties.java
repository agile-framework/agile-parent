package com.agileframework.agileclient.common.properties;

import com.agileframework.agileclient.common.annotation.Properties;
import org.activiti.engine.impl.history.HistoryLevel;

/**
 * Created by 佟盟 on 2018/4/4
 */
@Properties(prefix = "agile.activiti")
public class ActivitiConfigProperties {
    private static String databaseSchemaUpdate = "true";
    private static String history = HistoryLevel.FULL.getKey();
    private static MailConfigProperty mail = new MailConfigProperty();

    public static String getDatabaseSchemaUpdate() {
        return databaseSchemaUpdate;
    }

    public static void setDatabaseSchemaUpdate(String databaseSchemaUpdate) {
        ActivitiConfigProperties.databaseSchemaUpdate = databaseSchemaUpdate;
    }

    public static String getHistory() {
        return history;
    }

    public static void setHistory(String history) {
        ActivitiConfigProperties.history = history;
    }

    public static MailConfigProperty getMail() {
        return mail;
    }

    public static void setMail(MailConfigProperty mail) {
        ActivitiConfigProperties.mail = mail;
    }
}
