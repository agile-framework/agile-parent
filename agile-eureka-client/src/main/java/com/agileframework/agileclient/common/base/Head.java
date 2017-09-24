package com.agileframework.agileclient.common.base;

import com.agileframework.agileclient.common.util.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by 佟盟 on 2017/1/9
 */
public class Head implements Serializable {
    private static final long serialVersionUID = 97555324631150979L;
    private String url;
    private String ip;
    private String code;
    private String msg;

    public Head(RETURN returnState, HttpServletRequest request) {
        this.ip = ServletUtil.getCustomerIPAddr(request);
        this.url = request.getScheme() + "://" + ServletUtil.localhostFormat(request.getLocalAddr())+ ":" + request.getLocalPort() + request.getRequestURI();
        this.code = returnState.getCode();
        this.msg = returnState.getMsg();
    }

    public String getUrl() {
        return url;
    }

    public String getIp() {
        return ip;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}