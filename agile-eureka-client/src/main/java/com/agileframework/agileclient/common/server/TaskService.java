package com.agileframework.agileclient.common.server;

import com.agileframework.agileclient.common.annotation.Init;
import com.agileframework.agileclient.common.base.RETURN;
import com.agileframework.agileclient.common.factory.LoggerFactory;
import com.agileframework.agileclient.common.util.CacheUtil;
import com.agileframework.agileclient.common.util.FactoryUtil;
import com.agileframework.agileclient.common.util.ObjectUtil;
import com.agileframework.agileclient.mvc.model.entity.SysTaskEntity;
import com.agileframework.agileclient.mvc.model.entity.SysTaskTargetEntity;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by 佟盟 on 2018/2/2
 */
@Service("agileTaskService")
public class TaskService extends BusinessService<SysTaskEntity> {
    private Log log = LoggerFactory.createLogger("task", TaskService.class);
    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private static Map<String, TaskInfo> taskInfoMap = new HashMap<>();

    @Autowired
    public TaskService(ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    /**
     * 定时任务信息
     */
    public class TaskInfo extends SysTaskEntity {
        private TaskTrigger trigger; //触发器
        private TaskService.Job job; //任务
        private ScheduledFuture scheduledFuture;

        public TaskInfo(SysTaskEntity sysTaskEntity, TaskTrigger trigger, TaskService.Job job, ScheduledFuture scheduledFuture) {
            ObjectUtil.copyProperties(sysTaskEntity,this);
            this.trigger = trigger;
            this.job = job;
            this.scheduledFuture = scheduledFuture;
        }

        public TaskTrigger getTrigger() {
            return trigger;
        }

        public void setTrigger(TaskTrigger trigger) {
            this.trigger = trigger;
        }

        public TaskService.Job getJob() {
            return job;
        }

        public void setJob(TaskService.Job job) {
            this.job = job;
        }

        public ScheduledFuture getScheduledFuture() {
            return scheduledFuture;
        }

        public void setScheduledFuture(ScheduledFuture scheduledFuture) {
            this.scheduledFuture = scheduledFuture;
        }
    }

    /**
     * 定时任务触发器
     */
    public class TaskTrigger implements Trigger, Serializable{
        private String cron;
        private boolean sync;

        TaskTrigger(String cron, boolean sync){
            this.cron = cron;
            this.sync = sync;
        }
        @Override
        public Date nextExecutionTime(TriggerContext triggerContext) {
            CronTrigger cronTrigger = new CronTrigger(this.cron);
            return cronTrigger.nextExecutionTime(triggerContext);
        }

        public String getCron() {
            return cron;
        }

        public void setCron(String cron) {
            this.cron = cron;
        }

        public boolean isSync() {
            return sync;
        }

        public void setSync(boolean sync) {
            this.sync = sync;
        }
    }

    /**
     * 任务对象
     */
    public class Job implements Serializable, Runnable {
        private static final long serialVersionUID = 1352043270981352844L;

        private String taskName;
        private TaskTrigger trigger;
        private List<SysTaskTargetEntity> sysTaskTargetEntityList;

        Job(String taskName, TaskTrigger trigger, List<SysTaskTargetEntity> sysTaskTargetEntityList) {
            this.taskName = taskName;
            this.trigger = trigger;
            this.sysTaskTargetEntityList = sysTaskTargetEntityList;
        }

        @Override
        public void run() {
            synchronized(this) {
                //判断是否需要同步，同步情况下获取同步锁后方可执行，非同步情况下直接运行
                if (this.trigger.isSync()) {
                    //获取下次执行时间（秒）
                    long nextTime = (this.trigger.nextExecutionTime(new SimpleTriggerContext()).getTime() - new Date().getTime()) / 1000;

                    //如果抢到同步锁，设置锁定时间并直接运行
                    if (setNxLock(this.taskName, (int) nextTime)) {
                        invoke(sysTaskTargetEntityList);
                    }
                }else{
                    invoke(sysTaskTargetEntityList);
                }
            }
        }
    }

    /**
     * 逐个执行定时任务目标方法
     * @param sysTaskTargetEntityList 定时任务详情数据集
     */
    private void invoke(List<SysTaskTargetEntity> sysTaskTargetEntityList){
        try {
            //逐个执行定时任务目标方法
            for(int i=0;i<sysTaskTargetEntityList.size();i++){
                SysTaskTargetEntity sysTaskTargetEntity = sysTaskTargetEntityList.get(i);
                if(log.isInfoEnabled()){
                    log.info("开始定时任务:"+sysTaskTargetEntity.getName());
                }
                if(ObjectUtil.isEmpty(sysTaskTargetEntity))return;
                String className = sysTaskTargetEntity.getTargetPackage() + "." + sysTaskTargetEntity.getTargetClass();
                Class<?> clazz = Class.forName(className);
                Object targetBaen = FactoryUtil.getBean(clazz);
                Method taretMethod = clazz.getDeclaredMethod(sysTaskTargetEntity.getTargetMethod());
                taretMethod.setAccessible(true);
                taretMethod.invoke(targetBaen);
            }
        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * spring容器初始化时初始化全部定时任务
     */
    @Init
    public void init(){
        //获取持久层定时任务数据集
        List<SysTaskEntity> list = dao.findAll(SysTaskEntity.class);
        for (int i = 0 ; i < list.size();i++ ) {
            SysTaskEntity sysTaskEntity = list.get(i);
            addTask(sysTaskEntity);
        }
    }

    /**
     * 根据定时任务对象添加定时任务
     * @return 是否添加成功
     */
    private boolean addTask(SysTaskEntity sysTaskEntity){
        try {
            //获取定时任务详情列表
            List<SysTaskTargetEntity> sysTaskTargetEntityList = dao.findAll("select a.* from sys_task_target a left join sys_bt_task_target b on b.sys_task_target_id = a.sys_task_target_id where b.sys_task_id = ? order by b.order", SysTaskTargetEntity.class, sysTaskEntity.getSysTaskId());

            if(ObjectUtil.isEmpty(sysTaskTargetEntityList)){
                return false;
            }

            //新建定时任务触发器
            TaskTrigger trigger = new TaskTrigger(sysTaskEntity.getCron(),sysTaskEntity.getSync());

            //新建任务
            TaskService.Job job = new TaskService.Job(sysTaskEntity.getName(), trigger, sysTaskTargetEntityList);

            ScheduledFuture scheduledFuture = null;
            if(sysTaskEntity.getState()){
                scheduledFuture = threadPoolTaskScheduler.schedule(job, trigger);
            }

            //定时任务装入缓冲区
            taskInfoMap.put(sysTaskEntity.getSysTaskId().toString(), new TaskInfo(sysTaskEntity,trigger,job,scheduledFuture));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 根据定时任务对象添加定时任务
     * @return 是否添加成功
     */
    public RETURN addTask() {
        SysTaskEntity entity = ObjectUtil.getObjectFromMap(SysTaskEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        if(this.addTask(entity)){
            return RETURN.SUCCESS;
        }
        return RETURN.EXPRESSION;
    }

    /**
     * 删除定时任务
     * @return 是否成功
     */
    public RETURN removeTask(){
        String id = this.getInParam("id",String.class);
        if(this.removeTask(id)){
            this.dao.deleteById(SysTaskEntity.class,Integer.parseInt(id));
            return RETURN.SUCCESS;
        }
        return RETURN.EXPRESSION;
    }

    private boolean removeTask(String id){
        if(taskInfoMap.containsKey(id)){
            if(!stopTask(id))return false;
            taskInfoMap.remove(id);
        }
        return true;
    }

    /**
     * 停止定时任务
     * @return 是否成功
     */
    public RETURN stopTask(){
        String id = this.getInParam("id",String.class);
        if(this.stopTask(id)){
            SysTaskEntity entity = dao.findOne(SysTaskEntity.class, Integer.parseInt(id));
            entity.setState(false);
            dao.update(entity);
            return RETURN.SUCCESS;
        }
        return RETURN.EXPRESSION;
    }

    private boolean stopTask(String id){
        try {
            TaskInfo taskInfo = taskInfoMap.get(id);
            if(ObjectUtil.isEmpty(taskInfo))return true;
            ScheduledFuture future = taskInfo.getScheduledFuture();
            if(ObjectUtil.isEmpty(future))return true;
            future.cancel(Boolean.TRUE);
        }catch (Exception e){
            return false;
        }
        return true;
    }


    /**
     * 开启定时任务
     * @return 是否成功
     */
    public RETURN startTask(){
        try {
            String id = this.getInParam("id",String.class);
            TaskInfo taskInfo = taskInfoMap.get(id);
            if(ObjectUtil.isEmpty(taskInfo))return RETURN.EXPRESSION;
            ScheduledFuture future = this.threadPoolTaskScheduler.schedule(taskInfo.getJob(), taskInfo.getTrigger());
            taskInfo.setScheduledFuture(future);

            SysTaskEntity entity = dao.findOne(SysTaskEntity.class, Integer.parseInt(id));
            entity.setState(true);
            dao.update(entity);
            return RETURN.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return RETURN.EXPRESSION;
        }
    }

    /**
     * 更新定时任务
     * @return 是否成功
     */
    public RETURN updateTask() {
        SysTaskEntity entity = ObjectUtil.getObjectFromMap(SysTaskEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysTaskId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        if(this.updateTask(entity)){
            return RETURN.SUCCESS;
        }
        return RETURN.EXPRESSION;
    }

    private boolean updateTask(SysTaskEntity sysTaskEntity){
        try {
            if(!removeTask(sysTaskEntity.getSysTaskId().toString()))return false;
            return addTask(sysTaskEntity);
        }catch (Exception e){
            return false;
        }
    }

    public RETURN query(){
        int page = this.getInParam("page",Integer.class,0);
        int size = this.getInParam("size",Integer.class,10);
        this.setOutParam("queryList",dao.findAll(SysTaskEntity.class,page,size));
        return RETURN.SUCCESS;
    }
    /**
     * 获取分布式锁
     *
     * @param lockName 锁名称
     * @param second   加锁时间（秒）
     * @return 如果获取到锁，则返回lockKey值，否则为null
     */
    private boolean setNxLock(String lockName, int second) {
        synchronized(this) {
            //生成随机的Value值
            String lockKey = UUID.randomUUID().toString();
            //抢占锁
            Long lock = CacheUtil.setNx(lockName, lockKey);
            if (lock == 1) {
                //拿到Lock，设置超时时间
                CacheUtil.expire(lockName, second - 1);
            }
            return lock == 1;
        }
    }

}
