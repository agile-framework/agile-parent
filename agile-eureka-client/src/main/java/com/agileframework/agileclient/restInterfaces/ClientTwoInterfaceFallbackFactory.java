package com.agileframework.agileclient.restInterfaces;


import feign.hystrix.FallbackFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by 佟盟 on 2017/8/31
 */

@Component
public class ClientTwoInterfaceFallbackFactory implements FallbackFactory<ClientTwoInterface> {

    //日志工具
    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public ClientTwoInterface create(Throwable throwable) {
        return new ClientTwoInterface() {
            @Override
            public String queryClientTwoInfo() {
                return "false";
            }
        };
    }
}
