package com.agileframework.agileclient.common.servlet;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Druid Servlet配置页面认证信息
 * Created by 佟盟 on 2017/8/27
 */
@WebServlet(name = "DruidStatViewServlet",urlPatterns = "/druid/*",initParams = {
        @WebInitParam(name = "resetEnable",value = "true"),@WebInitParam(name = "loginUsername",value = "admin"),@WebInitParam(name = "loginPassword",value = "admin")
})
public class DruidStatViewServlet extends StatViewServlet {
}
