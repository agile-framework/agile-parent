package com.agileframework.agileclient.mvc.controller;

import com.agileframework.agileclient.common.base.Head;
import com.agileframework.agileclient.common.base.RETURN;
import com.agileframework.agileclient.common.base.Constant;
import com.agileframework.agileclient.common.exception.NoSuchRequestServiceException;
import com.agileframework.agileclient.common.server.ServiceInterface;
import com.agileframework.agileclient.common.util.FactoryUtil;
import com.agileframework.agileclient.common.util.ServletUtil;
import com.agileframework.agileclient.common.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 主控制层
 * Created by 佟盟 on 2017/8/22
 */
@Controller
public class MainController {

    private ThreadLocal<ServiceInterface> service = new ThreadLocal<>();

    /**
     * 非法请求处理器
     * @param request 请求对象
     * @return 响应视图
     */
    @RequestMapping(value = {"/","/*","/*/*/*/**"})
    public ModelAndView processor(HttpServletRequest request){
        //初始化参数
        ModelAndView modelAndView = new ModelAndView();

        //判断模块存在
        modelAndView.addObject(Constant.ResponseAbout.HEAD,new Head(RETURN.NO_COMPLETE,request));
        return modelAndView;
    }

    /**
     * agile框架处理器
     * @param request 请求对象
     * @param service 服务名
     * @param method 方法名
     * @param forward 转发信息
     * @return 响应试图数据
     * @throws IllegalAccessException 非法访问异常
     * @throws IllegalArgumentException 非法参数异常
     * @throws InvocationTargetException 调用目标异常
     * @throws NoSuchMethodException 没有这样的方法异常
     * @throws SecurityException 安全异常
     */
    @RequestMapping(value = "/{service}/{method}")
    public ModelAndView processor(
            HttpServletRequest request,
            @PathVariable String service,
            @PathVariable String method,
            @RequestParam(value = "forward", required = false) String forward,
            @RequestParam(value = "file-path", required = false) String filePath
    ) throws Throwable {
        //初始化参数
        ModelAndView modelAndView = new ModelAndView();//响应视图对象
        service =  StringUtil.toLowerName(service);//设置服务名
        method = StringUtil.toLowerName(method);//设置方法名
        initService(service);

        //调用目标方法前处理入参
        handleRequestUrl(request);

        //调用目标方法
        RETURN returnState = this.getService().executeMethod(method,this.getService());

        //判断是否存在文件上传
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (!StringUtil.isEmpty(filePath) && multipartResolver.isMultipart(request)){
            this.upLoadFile(request, filePath);
        }

        //判断是否转发
        if (!StringUtil.isEmpty(forward) && RETURN.SUCCESS.equals(returnState)) {
            StringBuilder url = new StringBuilder(forward);
            if(!forward.startsWith(Constant.RegularAbout.SLASH)){
                url.insert(0,Constant.RegularAbout.SLASH);
            }

            //过滤转发并获取请求参数，避免重复转发
            String beforeParam = request.getQueryString().replaceFirst(Constant.RegularAbout.AFTER_PARAM, Constant.RegularAbout.NULL);

            //服务间参数传递
            String afterParam = StringUtil.fromMapToUrl(this.getService().getOutParam());


            url = (StringUtil.isEmpty(beforeParam) && StringUtil.isEmpty(afterParam))?url
                    :(StringUtil.compareTo(beforeParam,afterParam)>0)?url.append(Constant.RegularAbout.QUESTION_MARK).append(beforeParam).append(Constant.RegularAbout.AND).append(afterParam)
                    :url.append(Constant.RegularAbout.QUESTION_MARK).append(afterParam).append(Constant.RegularAbout.AND).append(beforeParam);
            url = url.toString().endsWith(Constant.RegularAbout.AND)?url.deleteCharAt(url.lastIndexOf(Constant.RegularAbout.AND)):url;

            //转发
            modelAndView.setView(new RedirectView(url.toString()));

            return modelAndView;
        }

        //调用目标方法后处理视图
        modelAndView.addObject(Constant.ResponseAbout.HEAD, new Head(returnState, request));

        //响应数据装填
        modelAndView.addObject(Constant.ResponseAbout.RESULT, this.getService().getOutParam());

        return modelAndView;
    }

    /**
     * 根据服务名在Spring上下文中获取服务bean
     * @param serviceName   服务名
     */
    private void initService(String serviceName)throws NoSuchRequestServiceException {
        try {
            Object service = FactoryUtil.getBean(serviceName);
            this.setService((ServiceInterface) service);
        }catch (Exception e){
            throw new NoSuchRequestServiceException("[服务类:" + serviceName + "]于系统中不存在！");
        }
    }

    /**
     * 根据servlet请求、认证信息、目标服务名、目标方法名处理入参
     * @param request   servlet请求
     */
    private void handleRequestUrl(HttpServletRequest request) {
        HashMap<String, Object> inParam = new HashMap<>();

        //---------------------------------请求参数解析------------------------------------
        String queryString = request.getQueryString();
        if (!StringUtil.isEmpty(queryString)){
            String[] params = queryString.split(Constant.RegularAbout.AND),paramContainer;
            for (int i = 0 ; i < params.length ; i++) {
                paramContainer = params[i].split(Constant.RegularAbout.EQUAL);
                if (paramContainer.length == 2){
                    inParam.put(paramContainer[0],paramContainer[1]);
                }
            }
        }

        //将处理过的所有请求参数传入调用服务对象
        this.getService().setInParam(inParam);
    }

    /**
     * 文件下载
     * @param request  请求对象
     * @param path  文件存储路径
     */
    private void upLoadFile(HttpServletRequest request, String path){
        List<HashMap<String,Object>> list = new ArrayList<>();

        //转换成多部分request
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

        //获取所有文件提交的input名
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
        while (iterator.hasNext()) {
            List<MultipartFile> files = multipartHttpServletRequest.getFiles(iterator.next());
            for (int i = 0 ; i < files.size();i++){
                MultipartFile file = files.get(i);
                String fileName = file.getOriginalFilename();
                HashMap<String,Object> map = new HashMap<>();
                map.put(Constant.FileAbout.FILE_NAME,fileName);
                map.put(Constant.FileAbout.FILE_SIZE,file.getSize());
                map.put(Constant.FileAbout.CONTENT_TYPE,file.getContentType());

                //判断文件名
                if (StringUtil.isEmpty(fileName)) {
                    map.put(Constant.ResponseAbout.STATE,RETURN.EMPTY_FILENAME.getCode());
                    map.put(Constant.ResponseAbout.MSG,RETURN.EMPTY_FILENAME.getMsg());
                    list.add(map);
                    continue;
                }

                //判断文件内容为空
                if (file.isEmpty()) {
                    map.put(Constant.ResponseAbout.STATE,RETURN.EMPTY_FILE.getCode());
                    map.put(Constant.ResponseAbout.MSG,RETURN.EMPTY_FILE.getMsg());
                    list.add(map);
                    continue;
                }
                File newFile = new File(path,fileName);

                //尝试创建文件夹
                if (!newFile.getParentFile().exists() && !newFile.getParentFile().mkdirs()) {
                    map.put(Constant.ResponseAbout.STATE,RETURN.MADE_DIR_FAIL.getCode());
                    map.put(Constant.ResponseAbout.MSG,RETURN.MADE_DIR_FAIL.getMsg());
                    list.add(map);
                    continue;
                }

                //尝试文件复制
                try {
                    file.transferTo(newFile);
                    map.put(Constant.ResponseAbout.STATE,RETURN.UPLOAD_SUCCESS.getCode());
                    map.put(Constant.ResponseAbout.MSG,RETURN.UPLOAD_SUCCESS.getMsg());

                }catch (Exception e){
                    map.put(Constant.ResponseAbout.STATE,RETURN.UPLOAD_ERROR.getCode());
                    map.put(Constant.ResponseAbout.MSG,RETURN.UPLOAD_ERROR.getMsg());
                }
                list.add(map);
            }
        }
        this.getService().setOutParam(Constant.FileAbout.UP_LOUD_FILE_INFO,list);
    }

    /**
     * 文件下载
     * @param path 文件路径
     * @param fileName 文件名
     * @return 文件流
     * @throws FileNotFoundException 流异常
     */
    @RequestMapping("/download")
    private ResponseEntity<byte[]> downloadFile(@RequestParam(value = "path") String path ,@RequestParam(value = "fileName") String fileName) throws FileNotFoundException{
        File file = new File(path,fileName);
        byte[] byteFile;
        try {
            byteFile = FileUtils.readFileToByteArray(file);
        }catch (IOException e){
            throw new FileNotFoundException();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(file.length());
        headers.setContentDispositionFormData(Constant.HeaderAbout.ATTACHMENT,new String(fileName.getBytes(Charset.forName("UTF-8")),Charset.forName("ISO-8859-1")));
        return new ResponseEntity<>(byteFile, headers, HttpStatus.CREATED);
    }

    private ServiceInterface getService() {
        return service.get();
    }

    private void setService(ServiceInterface service) {
        this.service.set(service);
    }
}
