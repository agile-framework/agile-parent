package com.agileframework.agileclient.common.exception;

import com.agileframework.agileclient.common.base.Constant;
import com.agileframework.agileclient.common.base.Head;
import com.agileframework.agileclient.common.base.RETURN;
import com.agileframework.agileclient.common.factory.LoggerFactory;
import com.agileframework.agileclient.common.util.StringUtil;
import org.apache.commons.logging.Log;
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
    private Log logger = LoggerFactory.createLogger(Constant.FileAbout.SERVICE_LOGGER_FILE, this.getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();

        StackTraceElement msg = e.getStackTrace()[0];
        String exclass = msg.getClassName();
        String method = msg.getMethodName();
        int lineNumber = msg.getLineNumber();

        String msgStr = String.format("【异常定位:[类:%s]调用[方法:%s]时在第%s行代码处发生错误!】",exclass,method,lineNumber);

        if(!StringUtil.isEmpty(e.getMessage())){
            msgStr+=(String.format(" | 【详情:%s】", e.getMessage()));
        }
        logger.error(msgStr);
        
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
        }else if (e instanceof UnlawfulRequestException){
            r = RETURN.NO_COMPLETE;
        }else if (e instanceof NotFoundCacheProxyException){
            r = RETURN.NOT_FOUND_CACHEPROXY_EXPRESSION;
        }

        modelAndView.addObject(Constant.ResponseAbout.HEAD,new Head(r));
        modelAndView.addObject(Constant.ResponseAbout.RESULT,msgStr);
        return modelAndView;
    }
}
