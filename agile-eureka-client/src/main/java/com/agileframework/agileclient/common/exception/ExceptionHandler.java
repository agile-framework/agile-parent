package com.agileframework.agileclient.common.exception;

import com.agileframework.agileclient.common.base.Constant;
import com.agileframework.agileclient.common.base.Head;
import com.agileframework.agileclient.common.base.RETURN;
import com.agileframework.agileclient.common.exception.NoSuchRequestMethodException;
import com.agileframework.agileclient.common.exception.NoSuchRequestServiceException;
import com.agileframework.agileclient.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.DateTimeException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 佟盟 on 2017/2/23
 */
public class ExceptionHandler implements HandlerExceptionResolver {
    /**
     * 日志工具
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();//响应视图对象

        StackTraceElement[] msg = e.getStackTrace();
        StringBuilder msgStr = new StringBuilder();
        String exclass = msg[0].getClassName();
        String method = msg[0].getMethodName();
        msgStr.append("【异常定位:[类:").append(exclass).append("]调用[方法:").append(method).append("]时在第").append(msg[0].getLineNumber()).append("行代码处发生错误!】");

        if(!StringUtil.isEmpty(e.getMessage())){
            msgStr.append(" | 【详情:").append(e.getMessage()).append("】");
        }

        RETURN r = RETURN.EXPRESSION;
        if(e instanceof SQLException){
            r = RETURN.SQL_EXPRESSION;
        }else if(e instanceof DateTimeException){
            r = RETURN.DATETIME_EXPRESSION;
        }else if(e instanceof NullPointerException){
            r = RETURN.NULL_POINTER_EXPRESSION;
        }else if(e instanceof ParseException){
            r = RETURN.PARSE_EXPRESSION;
        }else if(e instanceof TimeoutException){
            r = RETURN.TIMEOUT_EXPRESSION;
        }else if(e instanceof IllegalAccessException){
            r = RETURN.IIIEGAL_ACCESS_EXPRESSION;
        }else if(e instanceof IllegalArgumentException){
            r = RETURN.IIIEGAL_ARGUMENT_EXPRESSION;
        }else if(e instanceof InvocationTargetException){
            r = RETURN.INVOCATION_TARGET_EXPRESSION;
        }else if(e instanceof NoSuchMethodException){
            r = RETURN.NO_SUCH_METHPD_EXPRESSION;
        }else if(e instanceof SecurityException){
            r = RETURN.SECURITY_EXPRESSION;
        }else if(e instanceof ClassCastException){
            r = RETURN.CLASS_CAST_EXPRESSION;
        }else if(e instanceof BeansException){
            r = RETURN.BEAN_EXPRESSION;
        }else if (e instanceof MaxUploadSizeExceededException){
            r = RETURN.MAX_UPLOAD_SIZE_EXPRESSION;
        }else if (e instanceof FileNotFoundException){
            r = RETURN.FILE_NOT_FOUND_EXPRESSION;
        }else if (e instanceof NoSuchRequestServiceException){
            r = RETURN.NO_SERVICE;
        }else if (e instanceof NoSuchRequestMethodException){
            r = RETURN.NO_METHOD;
        }

        modelAndView.addObject(Constant.ResponseAbout.HEAD,new Head(r,request));
        modelAndView.addObject(Constant.ResponseAbout.RESULT,msgStr);
        return modelAndView;
    }
}
