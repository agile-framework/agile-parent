package com.agileframework.agileclient.common.server;

import com.agileframework.agileclient.common.exception.ExceptionHandler;
import com.agileframework.agileclient.common.exception.NoSuchRequestMethodException;
import com.agileframework.agileclient.common.util.ArrayUtil;
import com.agileframework.agileclient.common.util.ObjectUtil;
import com.agileframework.agileclient.mvc.model.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 佟盟 on 2017/1/9
 */
public class MainService extends ExceptionHandler implements ServiceInterface {

    @Autowired
    public Dao dao;

    //输入
    private static ThreadLocal<Map<String, Object>> inParam = new ThreadLocal<>();

    //输出
    private static ThreadLocal<Map<String, Object>> outParam = ThreadLocal.withInitial(HashMap::new);

    /**
     * 根据对象及方法名通过反射执行该对象的指定方法
     * @param methodName 服务内部的具体方法名
     * @param object 服务子类对象，为解决Hystrix组件无法识别服务子类问题（识别成了父类）
     * @return 返回执行结果
     */
    @Transactional
    public Object executeMethod(String methodName, Object object) throws Throwable {

        initOutParam();

        try {
            Method method = this.getClass().getMethod(methodName);
            method.setAccessible(true);
            return method.invoke(object);
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
    public void setInParam(Map<String, Object> inParam) {
        MainService.inParam.set(inParam);
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
     * 服务中调用该方法获取入参
     * @param key 入参索引字符串
     * @return 入参值
     */
    protected String getInParam(String key,String defaultValue) {
        Object value = inParam.get().get(key);
        if(ObjectUtil.isEmpty(value)){
            return defaultValue;
        }
        return String.valueOf(value);
    }

    /**
     * 服务中调用该方法获取指定类型入参
     * @param key 入参索引字符串
     * @return 入参值
     */
    protected <T>T getInParam(String key,Class<T> clazz) {
        return getInParam(key,clazz,null);
    }


    /**
     * 服务中调用该方法获取指定类型入参
     * @param key 入参索引字符串
     * @return 入参值
     */
    protected <T>T getInParam(String key,Class<T> clazz,T defaultValue) {
        Object[] value = (Object[]) inParam.get().get(key);
        if(ObjectUtil.isEmpty(value)){
            return defaultValue;
        }
        Object result = ObjectUtil.cast(clazz, value[0]);
        return ObjectUtil.isEmpty(result)?null:(T)result;
    }

    /**
     * 服务中调用该方法获取字符串数组入参
     * @param key 入参索引字符串
     * @return 入参值
     */
    protected String[] getInParamOfArray(String key) {
        String[] value = (String[]) inParam.get().get(key);
        if(value!=null && value.length==1){
            return value;
        }
        return null;
    }

    /**
     * 服务中调用该方法获取指定类型入参
     * @param key 入参索引字符串
     * @return 入参值
     */
    protected <T>T[] getInParamOfArray(String key,Class<T> clazz) {
        String[] value = (String[]) inParam.get().get(key);
        if(value!=null && value.length>0){
            return ArrayUtil.cast(clazz,value);
        }
        return null;
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
    public Map<String, Object> getInParam() {
        return inParam.get();
    }

    /**
     * 控制层中调用该方法获取响应参数
     * @return 响应参数集
     */
    public Map<String, Object> getOutParam() {
        return outParam.get();
    }

    /**
     * 服务中调用该方法设置响应参数
     * @param key 参数索引字符串
     * @param value 参数值
     */
    public void setOutParam(String key, Object value) {
        outParam.get().put(key,value);
    }

    /**
     * 清理
     */
    public void initInParam(){
        inParam.remove();
    }

    /**
     * 清理
     */
    public void initOutParam(){
        outParam.remove();
    }
}
