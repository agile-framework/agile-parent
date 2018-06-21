package com.agileframework.agileclient.common.server;

import java.util.Map;

public interface ServiceInterface {
	//设置请求参数
	void setInParam(Map<String, Object> inParam);
	//设置响应参数
	void setOutParam(String key, Object value);
	//提取响应参数
	Map<String, Object> getOutParam();
	//提取响应参数
	Map<String, Object> getInParam();
	//调用请求方法
	Object executeMethod(String methodName, Object object) throws Throwable;
	//初始化入参
	public void initInParam();
	//初始化出参
	public void initOutParam();
}
