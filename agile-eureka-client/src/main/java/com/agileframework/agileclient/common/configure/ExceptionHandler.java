package com.agileframework.agileclient.common.configure;

import com.agileframework.agileclient.common.base.Head;
import com.agileframework.agileclient.common.base.RETURN;
import com.agileframework.agileclient.common.exception.NoSuchServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.lang.NonNullApi;
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
        if(e instanceof SQLException){
            logger.error(RETURN.SQL_EXPRESSION.getMsg());
            modelAndView.addObject("head",new Head(RETURN.SQL_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof DateTimeException){
            logger.error(RETURN.DATETIME_EXPRESSION.getMsg());
            modelAndView.addObject("head",new Head(RETURN.DATETIME_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof NullPointerException){
            logger.error(RETURN.NULL_POINTER_EXPRESSION.getMsg());
            modelAndView.addObject("head",new Head(RETURN.NULL_POINTER_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof ParseException){
            logger.error(RETURN.PARSE_EXPRESSION.getMsg());
            modelAndView.addObject("head",new Head(RETURN.PARSE_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof TimeoutException){
            logger.error(RETURN.TIMEOUT_EXPRESSION.getMsg());
            modelAndView.addObject("head",new Head(RETURN.TIMEOUT_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof IllegalAccessException){
            logger.error(RETURN.IIIEGAL_ACCESS_EXPRESSION.getMsg());
            modelAndView.addObject("head",new Head(RETURN.IIIEGAL_ACCESS_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof IllegalArgumentException){
            logger.error(RETURN.IIIEGAL_ARGUMENT_EXPRESSION.getMsg());
            modelAndView.addObject("head",new Head(RETURN.IIIEGAL_ARGUMENT_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof InvocationTargetException){
            logger.error(RETURN.INVOCATION_TARGET_EXPRESSION.getMsg());
            modelAndView.addObject("head",new Head(RETURN.INVOCATION_TARGET_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof NoSuchMethodException){
            logger.error(RETURN.NO_METHOD.getMsg());
            modelAndView.addObject("head",new Head(RETURN.NO_METHOD,request));
            return modelAndView;
        }else if(e instanceof SecurityException){
            logger.error(RETURN.SECURITY_EXPRESSION.getMsg());
            modelAndView.addObject("head",new Head(RETURN.SECURITY_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof ClassCastException){
            logger.error(RETURN.CLASS_CAST_EXPRESSION.getMsg());
            modelAndView.addObject("head",new Head(RETURN.CLASS_CAST_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof BeansException){
            logger.error(RETURN.BEAN_EXPRESSION.getMsg());
            modelAndView.addObject("head",new Head(RETURN.BEAN_EXPRESSION,request));
            return modelAndView;
        }else if (e instanceof MaxUploadSizeExceededException){
            logger.error(RETURN.MAX_UPLOAD_SIZE_EXPRESSION.getMsg());
            modelAndView.addObject("head",new Head(RETURN.MAX_UPLOAD_SIZE_EXPRESSION,request));
            return modelAndView;
        }else if (e instanceof FileNotFoundException){
            logger.error(RETURN.FILE_NOT_FOUND_EXPRESSION.getMsg());
            modelAndView.addObject("head",new Head(RETURN.FILE_NOT_FOUND_EXPRESSION,request));
            return modelAndView;
        }else if (e instanceof NoSuchServiceException){
            logger.error(RETURN.NO_SERVICE.getMsg());
            modelAndView.addObject("head",new Head(RETURN.NO_SERVICE,request));
            return modelAndView;
        }else {
            logger.error(RETURN.EXPRESSION.getMsg());
            modelAndView.addObject("head",new Head(RETURN.EXPRESSION,request));
            return modelAndView;
        }
    }
}
