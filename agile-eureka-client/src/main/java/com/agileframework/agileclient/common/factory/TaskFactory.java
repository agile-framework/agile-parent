package com.agileframework.agileclient.common.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by 佟盟 on 2017/11/29
 */
@Component
public class TaskFactory {
    private static TaskFactory taskFactory;
    @Autowired
    ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @PostConstruct
    void init(){
        taskFactory = this;
    }

    public static void insert(Runnable task, String cron){
        taskFactory.threadPoolTaskScheduler.setPoolSize(10);
        taskFactory.threadPoolTaskScheduler.initialize();
        taskFactory.threadPoolTaskScheduler.schedule(task, new CronTrigger(cron));
    }

    public static void update(Runnable task, String cron){
        ScheduledFuture<?> scheduledFuture = taskFactory.threadPoolTaskScheduler.schedule(task, new CronTrigger(cron));
        scheduledFuture.cancel(Boolean.TRUE);
        taskFactory.threadPoolTaskScheduler.schedule(task, new CronTrigger(cron));
    }

    public static void delete(Runnable task, String cron){
        ScheduledFuture<?> scheduledFuture = taskFactory.threadPoolTaskScheduler.schedule(task, new CronTrigger(cron));
        scheduledFuture.cancel(Boolean.TRUE);
    }
}
