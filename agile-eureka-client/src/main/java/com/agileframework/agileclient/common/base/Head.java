package com.agileframework.agileclient.common.base;

import com.agileframework.agileclient.common.util.ServletUtil;

import java.io.Serializable;

/**
 * Created by 佟盟 on 2017/1/9
 */
public class Head implements Serializable {
    private static final long serialVersionUID = 97555324631150979L;
    private String ip;
    private String code;
    private String msg;

    public Head(RETURN returnState) {
        this.code = returnState.getCode();
        this.msg = returnState.getMsg();
        this.ip = ServletUtil.getLocalIP();
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