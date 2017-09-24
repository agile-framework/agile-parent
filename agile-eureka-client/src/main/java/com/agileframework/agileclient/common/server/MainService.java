package com.agileframework.agileclient.common.server;

import com.agileframework.agileclient.common.exception.ExceptionHandler;
import com.agileframework.agileclient.common.base.RETURN;
import com.agileframework.agileclient.common.exception.NoSuchRequestMethodException;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.HashMap;

/**
 * Created by 佟盟 on 2017/1/9
 */
public class MainService extends ExceptionHandler implements ServiceInterface {

    //日志工具
    private ThreadLocal<org.slf4j.Logger> logger = new ThreadLocal<>();

    //输入
    private ThreadLocal<HashMap<String, Object>> inParam = new ThreadLocal<>();

    //输出
    private ThreadLocal<HashMap<String, Object>> outParam = ThreadLocal.withInitial(HashMap::new);

    /**
     * 根据对象及方法名通过反射执行该对象的指定方法
     * @param methodName 服务内部的具体方法名
     * @param object 服务子类对象，为解决Hystrix组件无法识别服务子类问题（识别成了父类）
     * @return 返回执行结果
     */
    @Transactional
    public RETURN executeMethod(String methodName,Object object) throws Throwable {
        //初始化日志控件
        setLogger(LoggerFactory.getLogger(this.getClass()));

        try {
            Method method = this.getClass().getDeclaredMethod(methodName);
            //取消安全检测，提高性能
            method.setAccessible(true);
            return (RETURN) method.invoke(object);
        }catch (NoSuchMethodException e){
            throw new NoSuchRequestMethodException("[方法:" + methodName + "]于[服务类:" + this.getClass().getName() + "]中不存在！");
        }catch (InvocationTargetException e){
            throw e.getTargetException();
        }
    }

    /**
     * 控制层中调用该方法设置服务入参
     * @param inParam 参数集
     */
    public void setInParam(HashMap<String, Object> inParam) {
        this.inParam.set(inParam);
    }

    /**
     * 服务中调用该方法获取入参
     * @param key 入参索引字符串
     * @return 入参值
     */
    protected Object getInParam(String key) {
        return inParam.get().get(key);
    }

    /**
     * 服务中调用该方法获取字符串入参
     * @param key 入参索引字符串
     * @return 入参值
     */
    protected String getInParamOfString(String key) {
        return String.valueOf(inParam.get().get(key));
    }

    /**
     * 服务中调用该方法获取字符串入参
     * @param key 入参索引字符串
     * @param defaultValue 默认值
     * @return 入参值
     */
    protected String getInParamOfString(String key,String defaultValue) {
        return containsKey(key)?String.valueOf(inParam.get().get(key)):defaultValue;
    }

    /**
     * 服务中调用该方法获取整数入参
     * @param key 入参索引字符串
     * @return 入参值
     */
    protected int getInParamOfInteger(String key) {
        return Integer.parseInt(getInParamOfString(key));
    }

    /**
     * 服务中调用该方法获取整数入参
     * @param key 入参索引字符串
     * @param defaultValue 默认值
     * @return 入参值
     */
    protected int getInParamOfInteger(String key,int defaultValue) {
        return containsKey(key)?Integer.parseInt(getInParamOfString(key)):defaultValue;
    }

    /**
     * 服务中调用该方法获取浮点入参
     * @param key 入参索引字符串
     * @param defaultValue 默认值
     * @return 入参值
     */
    protected float getInParamOfFloat(String key,float defaultValue) {
        return containsKey(key)?Float.parseFloat(getInParamOfString(key)):defaultValue;
    }

    /**
     * 服务中调用该方法获取浮点入参
     * @param key 入参索引字符串
     * @return 入参值
     */
    protected float getInParamOfFloat(String key) {
        return Float.parseFloat(getInParamOfString(key));
    }

    /**
     * 服务中调用该方法获取日期入参
     * @param key 入参索引字符串
     * @param defaultValue 默认值
     * @return 入参值
     */
    protected Date getInParamOfDate(String key,Date defaultValue) {
        return containsKey(key)?Date.valueOf(getInParamOfString(key)):defaultValue;
    }

    /**
     * 服务中调用该方法获取日期入参
     * @param key 入参索引字符串
     * @return 入参值
     */
    protected Date getInParamOfDate(String key) {
        return Date.valueOf(getInParamOfString(key));
    }

    /**
     * 服务中调用该方法获取长整形入参
     * @param key 入参索引字符串
     * @param defaultValue 默认值
     * @return 入参值
     */
    protected long getInParamOfLong(String key,long defaultValue) {
        return containsKey(key)?Long.valueOf(getInParamOfString(key)):defaultValue;
    }

    /**
     * 服务中调用该方法获取长整形入参
     * @param key 入参索引字符串
     * @return 入参值
     */
    protected long getInParamOfLong(String key) {
        return Long.valueOf(getInParamOfString(key));
    }

    /**
     * 服务中调用该方法获取布尔形入参
     * @param defaultValue 默认值
     * @return 入参值
     */
    protected boolean getInParamOfBoolean(String key,boolean defaultValue) {
        return containsKey(key)?Boolean.valueOf(getInParamOfString(key)):defaultValue;
    }

    /**
     * 服务中调用该方法获取布尔形入参
     * @param key 入参索引字符串
     * @return 入参值
     */
    protected boolean getInParamOfBoolean(String key) {
        return Boolean.valueOf(getInParamOfString(key));
    }


    /**
     * 服务中调用该方判断是否存在入参
     * @param key 入参索引字符串
     * @return 入参值
     */
    protected boolean containsKey(String key) {
        return inParam.get().containsKey(key);
    }

    /**
     * 服务中调用该方法获取入参集合
     * @return 入参集合
     */
    public HashMap<String, Object> getInParam() {
        return inParam.get();
    }

    /**
     * 控制层中调用该方法获取响应参数
     * @return 响应参数集
     */
    public HashMap<String, Object> getOutParam() {
        return this.outParam.get();
    }

    /**
     * 服务中调用该方法设置响应参数
     * @param key 参数索引字符串
     * @param value 参数值
     */
    public void setOutParam(String key, Object value) {
        this.outParam.get().put(key,value);
    }

    /**
     * 日志工具
     */
    public void setLogger(org.slf4j.Logger logger){
        this.logger.set(logger);
    }

    /**
     * 日志工具
     */
    public org.slf4j.Logger getLogger(){
        return this.logger.get();
    }

}
