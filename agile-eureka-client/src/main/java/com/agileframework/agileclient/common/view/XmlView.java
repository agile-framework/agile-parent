package com.agileframework.agileclient.common.view;

import com.agileframework.agileclient.common.util.JSONUtil;
import net.sf.json.xml.XMLSerializer;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * Created by 佟盟 on 2017/8/1
 */
@Component
public class XmlView extends AbstractView {
    public static final String DEFAULT_CONTENT_TYPE = "application/xml";

    public XmlView() {
        this.setContentType(DEFAULT_CONTENT_TYPE);
        this.setExposePathVariables(false);
    }
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        for (Map.Entry<String,Object> o:model.entrySet()) {
            if(o.getValue() instanceof BindingResult){
                model.remove(o.getKey());
            }
        }
        XMLSerializer xmlSerializer = new XMLSerializer();
//        xmlSerializer.setTypeHintsEnabled(false);
//        xmlSerializer.setTypeHintsCompatibility(false);
        xmlSerializer.setObjectName("Entity");
        xmlSerializer.setArrayName("Array");
        xmlSerializer.setElementName("Element");
        String xml = xmlSerializer.write(JSONUtil.toJSON(model));
        baos.write(xml.getBytes(response.getCharacterEncoding()));
        this.setResponseContentType(request, response);
        response.setContentLength(baos.size());
        baos.writeTo(response.getOutputStream());
    }
}
