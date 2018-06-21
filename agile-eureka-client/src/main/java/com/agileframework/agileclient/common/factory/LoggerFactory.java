package com.agileframework.agileclient.common.factory;

import com.agileframework.agileclient.common.util.ObjectUtil;
import com.agileframework.agileclient.common.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.filter.LevelRangeFilter;
import org.apache.logging.log4j.core.layout.PatternLayout;

/**
 * Created by 佟盟 on 2017/2/23
 * 日志工厂
 */
public class LoggerFactory {
    private static LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
    private static Configuration config = ctx.getConfiguration();
    private static String path = System.getProperty("webapp.root");
    private LoggerFactory() {}
    private static void createLogger(String baseName, Level[] levels, String packagePath) {
        //创建输出格式
        Layout layout = PatternLayout.newBuilder().withConfiguration(config).withPattern("%-d{yyyy-MM-dd HH:mm:ss} [ %p ] [ %c ] %m%n").build();
        baseName = StringUtil.camelToUnderline(baseName);
        AppenderRef[] refs = new AppenderRef[levels.length*2];
        String[] appenders = new String[levels.length*2];
        for (int i = 0 ; i < levels.length;i ++){
            String targetFileName = (baseName + "_" + levels[i].name()).toLowerCase();
            if(ObjectUtil.isEmpty(config.getAppender(targetFileName))){
                //输出引擎
                appenders[i*2] = createFileAppender(baseName,targetFileName,layout);
                refs[i*2] = AppenderRef.createAppenderRef(appenders[i*2], null, null);

                appenders[i*2+1] = createConsoleAppender(targetFileName,layout);
                refs[i*2+1] = AppenderRef.createAppenderRef(appenders[i*2+1], null, null);
            }
        }

        LoggerConfig loggerConfig = LoggerConfig.createLogger(Boolean.FALSE, Level.ALL,  packagePath,"true", refs, null, config, null);
        for (int i = 0 ; i < levels.length;i ++) {
            if(ObjectUtil.isEmpty(appenders[i]))break;
            Filter filter = LevelRangeFilter.createFilter(levels[i], levels[i], Filter.Result.ACCEPT, Filter.Result.DENY);
            loggerConfig.addAppender(config.getAppender(appenders[i*2]), levels[i], filter);
            loggerConfig.addAppender(config.getAppender(appenders[i*2+1]), levels[i], filter);
        }
        config.addLogger(packagePath,loggerConfig);
        ctx.updateLoggers();
    }
    private static String createFileAppender(String baseName, String fileName, Layout layout){
        String name = fileName + "_file";
        if(ObjectUtil.isEmpty(config.getAppender(name))){
            @SuppressWarnings("unchecked")
            Appender appender = FileAppender.newBuilder().withName(name).withFileName(String.format(path + "/logs/"+baseName+"/%s.log", fileName)).withAppend(true).withLocking(false).withIgnoreExceptions(true).withBufferedIo(true).withLayout(layout).build();
            appender.start();
            config.addAppender(appender);
        }
        return name;
    }
    private static String createConsoleAppender(String name, Layout layout){
        name += "_console";
        if(ObjectUtil.isEmpty(config.getAppender(name))) {
            @SuppressWarnings("unchecked")
            Appender consoleAppender = ConsoleAppender.newBuilder().withName(name).setTarget(ConsoleAppender.Target.SYSTEM_OUT).withIgnoreExceptions(true).withBufferedIo(true).withLayout(layout).build();
            consoleAppender.start();
            config.addAppender(consoleAppender);
        }
        return name;
    }

    public static void stop(String fileName) {
        Level[] levels = Level.values();
        for (int i = 0 ; i < levels.length;i++){
            String filename = StringUtil.camelToUnderline(fileName) + "_" + levels[i].name();
            config.getRootLogger().removeAppender(filename);
        }
        ctx.updateLoggers();
    }

    public static Log createLogger(String fileName, Class clazz) {
        createLogger(fileName,new Level[]{Level.INFO, Level.ERROR},clazz.getName());
        return LogFactory.getLog(clazz);
    }

    public static Log createLogger(String fileName, Class clazz, Level[] levels) {
        createLogger(fileName,levels,null);
        return LogFactory.getLog(clazz);
    }

    public static Log createLogger(String fileName, Class clazz, Level[] levels, String packagePath) {
        createLogger(fileName,levels,packagePath);
        return LogFactory.getLog(clazz);
    }
    public static Log createLogger(String fileName, Class clazz, String packagePath) {
        createLogger(fileName,new Level[]{Level.INFO, Level.ERROR},packagePath);
        return LogFactory.getLog(clazz);
    }
    static {
//        createLogger("springmvc",DispatcherServlet.class,new Level[]{Level.ALL,Level.DEBUG,Level.INFO,Level.ERROR},"org.springframework.web.servlet");
//        createLogger("activiti",DispatcherServlet.class,new Level[]{Level.ALL,Level.DEBUG,Level.INFO,Level.ERROR},"org.activiti");
    }
}
