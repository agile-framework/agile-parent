package com.agileframework.agileclient.common.aop;

import com.agileframework.agileclient.common.util.ObjectUtil;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 佟盟 on 2017/9/24
 */
@Component
public class LogProcessor {


    @Async
    void buildLog(String serviceName, String methodName, Logger logger, HashMap<String,Object> inParam, HashMap<String,Object> outParam,String ip,StringBuffer url) {
        StringBuilder logMsg = new StringBuilder("\nIP:");
        logMsg.append(ip);
        logMsg.append("\nURL:").append(url);
        logMsg.append("\n服务:").append(serviceName).append("\n方法:").append(methodName).append("\n入参:");
        if (ObjectUtil.isEmpty(inParam)){
            logMsg.append("无");
        }
        for (Map.Entry<String,Object> entry:inParam.entrySet()){
            logMsg.append("{").append(entry.getKey()).append("=>").append(entry.getValue()).append("}");
        }
        logMsg.append("\n出参:");
        if (ObjectUtil.isEmpty(outParam)){
            logMsg.append("无");
        }
        for (Map.Entry<String,Object> entry:outParam.entrySet()){
            logMsg.append("{").append(entry.getKey()).append("=>").append(entry.getValue()).append("}");
        }
        logMsg.append("\n<---------------------------------------------------------------------->\n");
        logger.info(logMsg.toString());
    }
}
