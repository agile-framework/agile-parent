package com.agileframework.agileclient.mvc.controller;

import com.agileframework.agileclient.common.base.Constant;
import com.agileframework.agileclient.common.base.Head;
import com.agileframework.agileclient.common.base.RETURN;
import com.agileframework.agileclient.common.base.RequestWrapper;
import com.agileframework.agileclient.common.exception.NoSuchRequestServiceException;
import com.agileframework.agileclient.common.exception.UnlawfulRequestException;
import com.agileframework.agileclient.common.server.ServiceInterface;
import com.agileframework.agileclient.common.util.FactoryUtil;
import com.agileframework.agileclient.common.util.FileUtil;
import com.agileframework.agileclient.common.util.ObjectUtil;
import com.agileframework.agileclient.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 主控制层
 * Created by 佟盟 on 2017/8/22
 */
@Controller
public class MainController {

    //服务缓存变量
    private static ThreadLocal<ServiceInterface> service = new ThreadLocal<>();

    //request缓存变量
    private static ThreadLocal<HttpServletRequest> request = new ThreadLocal<>();

    /**
     * 非法请求处理器
     */
    @RequestMapping(value = {"/","/*","/*/*/*/**"})
    public void processor() throws UnlawfulRequestException {
        throw new UnlawfulRequestException();
    }

    /**
     * agile框架处理器
     * @param service 服务名
     * @param method 方法名
     * @return 响应试图数据
     */
    @RequestMapping(value = "/{service}/{method}")
    public Object processor(
            HttpServletRequest currentRequest,
            HttpServletResponse currentResponse,
            @PathVariable String service,
            @PathVariable String method
    ) throws Throwable {
        //清理缓存
        clear();

        //初始化参数
        service =  StringUtil.toLowerName(service);//设置服务名
        method = StringUtil.toLowerName(method);//设置方法名
        initService(service);
        request.set(currentRequest);

        //处理入参
        handleInParam();

        //调用目标方法
        Object returnData = getService().executeMethod(method,getService());

        //获取出参
        Map<String, Object> outParam = getService().getOutParam();

        //判断是否跳转
        if(outParam.containsKey(Constant.RegularAbout.FORWARD)){
            return jump(Constant.RegularAbout.FORWARD);
        }
        if(outParam.containsKey(Constant.RegularAbout.REDIRECT)){
            return jump(Constant.RegularAbout.REDIRECT);
        }

        //处理响应视图
        ModelAndView modelAndView = new ModelAndView();//响应视图对象

        if(ObjectUtil.isEmpty(returnData)){
            modelAndView.addObject(Constant.ResponseAbout.HEAD, new Head(RETURN.SUCCESS));
        } else if(returnData instanceof RETURN){
            modelAndView.addObject(Constant.ResponseAbout.HEAD, new Head((RETURN)returnData));
        } else {
            modelAndView.addObject(Constant.ResponseAbout.HEAD, new Head(RETURN.SUCCESS));
            outParam.put(Constant.ResponseAbout.RETURN,returnData);
        }

        modelAndView.addObject(Constant.ResponseAbout.RESULT, outParam);
        //清理缓存
        clear();

        return modelAndView;
    }

    /**
     * 由于线程池的使用与threadLocal冲突,前后需要清理缓存
     */
    private void clear(){
        service.remove();
        request.remove();
    }

    /**
     * 转发
     * @param jumpMethod 跳转方式
     */
    private ModelAndView jump(String jumpMethod){
        Map<String, Object> outParam = getService().getOutParam();
        Map<String, Object> inParam = getService().getInParam();

        ModelAndView model = new ModelAndView(exposeJumpUrl(jumpMethod,outParam));
        model.addAllObjects(outParam);
        model.addAllObjects(inParam);
        return model;
    }

    /**
     * 处理跳转地址及参数
     * @param jumpMethod 跳转方式
     * @param outParam 跳转之前的输出参数
     * @return 用于跳转的目标地址
     */
    private String exposeJumpUrl(String jumpMethod,Map<String, Object> outParam){
        //获取跳转地址
        String resourceUrl = outParam.get(jumpMethod).toString();

        StringBuilder url = new StringBuilder(jumpMethod+ Constant.RegularAbout.COLON);
        //补充斜杠
        if(!resourceUrl.startsWith(Constant.RegularAbout.HTTP) && !resourceUrl.startsWith(Constant.RegularAbout.SLASH)){
            url.append(Constant.RegularAbout.SLASH);
        }
        url.append(resourceUrl);
        //补充问号
        if(!resourceUrl.contains(Constant.RegularAbout.QUESTION_MARK)){
            url.append(Constant.RegularAbout.QUESTION_MARK);
        }
        //移除本跳转防止死循环
        outParam.remove(jumpMethod);
        return url.toString();
    }

    /**
     * 根据服务名在Spring上下文中获取服务bean
     * @param serviceName   服务名
     */
    private void initService(String serviceName)throws NoSuchRequestServiceException {
        try {
            service.set((ServiceInterface) FactoryUtil.getBean(serviceName));
        }catch (Exception e){
            throw new NoSuchRequestServiceException();
        }
    }

    /**
     * 根据servlet请求、认证信息、目标服务名、目标方法名处理入参
     */
    private void handleInParam() {
        getService().initInParam();
        HttpServletRequest currentRequest = request.get();
        Map<String,Object> inParam = new HashMap<>();
        Map<String, String[]> parameterMap = currentRequest.getParameterMap();
        if (parameterMap.size()>0){
            for (Map.Entry<String,String[]> map:parameterMap.entrySet() ) {
                inParam.put(map.getKey(),map.getValue());
            }
        }

        if (currentRequest instanceof RequestWrapper){
            Map<String, Object> forwardMap = ((RequestWrapper) currentRequest).getForwardParameterMap();
            for (Map.Entry<String,Object> map:forwardMap.entrySet() ) {
                inParam.put(map.getKey(),map.getValue());
            }
        }

        //判断是否存在文件上传
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(currentRequest.getSession().getServletContext());
        if (multipartResolver.isMultipart(currentRequest)){
            inParam.putAll(FileUtil.getFileFormRequest(currentRequest));
        }

        //将处理过的所有请求参数传入调用服务对象
        getService().setInParam(inParam);
    }

    private ServiceInterface getService() {
        return service.get();
    }
}
