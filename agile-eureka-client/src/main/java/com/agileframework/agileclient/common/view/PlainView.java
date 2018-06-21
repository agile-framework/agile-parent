package com.agileframework.agileclient.common.view;

import com.agileframework.agileclient.common.base.Constant;
import com.agileframework.agileclient.common.util.ClassUtil;
import com.agileframework.agileclient.common.util.MapUtil;
import com.agileframework.agileclient.common.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * Created by 佟盟 on 2017/8/1
 */
@Component
public class PlainView extends AbstractView {
    public static final String DEFAULT_CONTENT_TYPE = "text/plain";

    public PlainView() {
        this.setContentType(DEFAULT_CONTENT_TYPE);
        this.setExposePathVariables(false);
    }
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        if(model.containsKey(Constant.ResponseAbout.RESULT)){
            Object r = model.get(Constant.ResponseAbout.RESULT);
            if(MapUtil.isMap(r)){
                for (Map.Entry<String,Object> param: ((Map<String,Object>)r).entrySet()) {
                    Object value = param.getValue();
                    if(ClassUtil.isPrimitiveOrWrapper(value.getClass()) || StringUtil.isString(value)){
                        baos.write(value.toString().getBytes(response.getCharacterEncoding()));
                    }
                }
            }
        }
        response.setContentType("text/html");
        response.setContentLength(baos.size());
        baos.writeTo(response.getOutputStream());
    }
}
