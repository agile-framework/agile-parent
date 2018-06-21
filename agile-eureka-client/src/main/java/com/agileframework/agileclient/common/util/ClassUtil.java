package com.agileframework.agileclient.common.util;

import org.springframework.util.ClassUtils;

/**
 * Created by mydeathtrial on 2017/4/24
 */
public class ClassUtil extends ClassUtils {

    /**
     * 基本类型转换包装类
     * @param name 类型名字
     * @return 包装类名字
     */
    public static String toWrapperNameFromName(String name){
        if ("byte".equals(name))return "Byte";
        if ("short".equals(name))return "Short";
        if ("int".equals(name))return "Integer";
        if ("long".equals(name))return "Long";
        if ("float".equals(name))return "Float";
        if ("double".equals(name))return "Double";
        if ("boolean".equals(name))return "Boolean";
        if ("char".equals(name))return "Character";
        if ("String".equals(name))return "String";
        return name;
    }

}
