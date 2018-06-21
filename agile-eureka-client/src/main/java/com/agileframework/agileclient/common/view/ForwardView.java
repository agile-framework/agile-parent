package com.agileframework.agileclient.common.view;

import com.agileframework.agileclient.common.base.RequestWrapper;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by 佟盟 on 2018/3/26
 */
public class ForwardView extends AbstractView {
    private String url;

    public ForwardView(String url) {
        this.url = url;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestWrapper requestWrapper = new RequestWrapper(request,model);
        //处理参数
        RequestDispatcher rd = this.getRequestDispatcher(requestWrapper, getUrl());
        if (rd == null) {
            throw new ServletException("转发失败，地址:"+getUrl());
        }
        rd.forward(requestWrapper, response);
    }

    @Nullable
    private RequestDispatcher getRequestDispatcher(HttpServletRequest request, String path) {
        return request.getRequestDispatcher(path);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
