package com.agileframework.agilegateway.base;

/**
 * Created by 佟盟 on 2017/1/9
 */
public final class RETURN {
    //默认响应状态码
    private String code;

    //默认响应信息
    private String msg;

    //请求类响应状态
    public final static RETURN ERROR = new RETURN("400000","您所请求的服务端点发生异常");

    public RETURN(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
