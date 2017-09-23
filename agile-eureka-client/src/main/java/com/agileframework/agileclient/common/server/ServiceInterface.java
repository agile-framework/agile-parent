package com.agileframework.agileclient.common.server;

import com.agileframework.agileclient.common.base.RETURN;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public interface ServiceInterface {
	//设置请求参数
	void setInParam(HashMap<String, Object> inParam);
	//设置响应参数
	void setOutParam(String key, Object value);
	//提取响应参数
	HashMap<String, Object> getOutParam();
	//调用请求方法
	RETURN executeMethod(String methodName,Object object) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,NoSuchMethodException, SecurityException;
}
