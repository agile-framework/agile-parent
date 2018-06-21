package com.agileframework.agileclient.common.util;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

/**
 * Created by mydeathtrial on 2017/5/9
 */
public class JSONUtil{

    /**
     * 转换json字符串
     * @param object 转换源
     * @return json串
     */
    public static String toJSONString(Object object) {
        if(StringUtil.isString(object)) return object.toString();
        if(JSONUtils.isArray(object)){
            return JSONArray.fromObject(object).toString();
        }else {
            try {
                return JSONObject.fromObject(object).toString();
            }catch (Exception e){
                return null;
            }
        }
    }

    /**
     * 转换json对象
     * @param object 转换源
     * @return json对象
     */
    public static JSONObject toJSON(Object object){
        return JSONObject.fromObject(object);
    }
}
