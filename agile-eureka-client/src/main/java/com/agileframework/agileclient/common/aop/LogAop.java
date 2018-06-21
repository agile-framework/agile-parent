package com.agileframework.agileclient.common.aop;

import com.agileframework.agileclient.common.base.Constant;
import com.agileframework.agileclient.common.factory.LoggerFactory;
import com.agileframework.agileclient.common.factory.PoolFactory;
import com.agileframework.agileclient.common.server.MainService;
import com.agileframework.agileclient.common.util.JSONUtil;
import com.agileframework.agileclient.common.util.ServletUtil;
import org.apache.commons.logging.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by 佟盟 on 2017/9/24
 * 处理服务调用日志部分
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    HttpServletRequest request;

    //日志线程池
    private static ThreadPoolExecutor pool = PoolFactory.pool(5, 30, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(),new ThreadPoolExecutor.DiscardPolicy());

    //日志模板
    private static final String logTemplate = "\nIP:%s\nURL:%s\n服务:%s\n方法:%s\n入参:%s\n出参:%s\n<---------------------------------------------------------------------->";

    /**
     * 服务切面
     */
    @Pointcut(value = "execution(* com.agileframework.agileclient.common.server.ServiceInterface.executeMethod(..))")
    public void servicePointCut() {
    }

    /**
     * 后置通知
     * @param joinPoint 切入点
     */
    @After(value = "servicePointCut()")
    public void logAround(JoinPoint joinPoint) throws Throwable {
        Object service = joinPoint.getTarget();
        Object inParam = MainService.class.getDeclaredMethod("getInParam").invoke(service);
        Object outParam = MainService.class.getDeclaredMethod("getOutParam").invoke(service);
        LogThread thread = new LogThread(service, joinPoint.getArgs()[0].toString(), ServletUtil.getCustomerIPAddr(request), request.getRequestURL(),inParam,outParam,request);
        pool.execute(thread);
    }

    /**
     * 日志线程
     */
    private class LogThread implements Runnable {
        Object service;
        String methodName;
        String ip;
        StringBuffer url;
        Object inParam;
        Object outParam;
        HttpServletRequest request;
        LogThread(Object service, String methodName, String ip, StringBuffer url, Object inParam, Object outParam, HttpServletRequest request) {
            this.service = service;
            this.methodName = methodName;
            this.ip = ip;
            this.url = url;
            this.inParam = inParam;
            this.outParam = outParam;
            this.request = request;
        }

        @Override
        public void run() {
            try {
                Class<?> serviceClass = service.getClass();
                Log logger = LoggerFactory.createLogger(Constant.FileAbout.SERVICE_LOGGER_FILE, serviceClass);
                if(logger.isInfoEnabled()){
                    logger.info(String.format(logTemplate,ip,url,serviceClass.getSimpleName(),methodName, JSONUtil.toJSONString(inParam), JSONUtil.toJSONString(outParam)));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
