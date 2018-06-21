package com.agileframework.agileclient.common.server;

import com.agileframework.agileclient.common.util.ObjectUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


/**
 * 日志服务
 * Created by mydeathtrial on 2017/3/21
 */
public class LogService {
    public static void createLog(String type, Object oldObject, Object newObject, String targetType, String targetCode){
        try {
            if (((ObjectUtil.compareClass(oldObject, newObject) && !ObjectUtil.compareValue(oldObject, newObject)) || ObjectUtil.isEmpty(oldObject)) != ObjectUtil.isEmpty(newObject)){

                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                Thread thread = Thread.currentThread();
                Object logId = request.getAttribute(String.valueOf(thread.getId()));
                if(ObjectUtil.isEmpty(logId)){
//                    TSysLogEntity logEntity = new TSysLogEntity();
//                    logEntity.setBusinessCode("ZYSM02");
//                    logEntity.setObjectType(targetType);
//                    logEntity.setObjectCode(targetCode);
//                    logEntity.setStaffId(1);
//                    logEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
//                    //dao操作保存TSysLogEntity
//                    logId = logEntity.getId();
//                    request.setAttribute(thread.getName(),logEntity.getId());
                }


                List<Map<String, Object>> propertiesList = ObjectUtil.getDifferenceProperties(oldObject, newObject);
                for (Map<String,Object> map:propertiesList) {
//                    TSysLogDetailEntity logDetailEntity = new TSysLogDetailEntity();
//                    logDetailEntity.setLogId(Integer.parseInt(logId.toString()));
//                    logDetailEntity.setTableName(oldObject.getClass().getName());
//                    logDetailEntity.setColumName(String.valueOf(map.get("propertyName")));
//                    logDetailEntity.setColumType(String.valueOf(map.get("propertyType")));
//                    logDetailEntity.setNewValue(String.valueOf(map.get("oldValue")));
//                    logDetailEntity.setNewValue(String.valueOf(map.get("newValue")));
                }
            }


        }catch (Exception e){
//            return null;
        }
//        return null;
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

//        SysAuthoritiesEntity s = new SysAuthoritiesEntity();
//        s.setAuthorityDesc("1111111111");
//        s.setAuthorityMark("2222222222");
//        s.setAuthorityName("3333333333");
//        s.setEnable(true);
//        s.setIssys(true);
//        s.setMessage("4444444444");
//        s.setModuleId("12");
//        SysAuthoritiesEntity s1 = new SysAuthoritiesEntity();
//        s1.setAuthorityDesc("1111111112");
//        s1.setAuthorityMark("2222222223");
//        s1.setAuthorityName("3333333333");
//        s1.setEnable(true);
//        s1.setIssys(true);
//        s1.setMessage("4444444444");
//        s1.setModuleId("12");
//        createLog("ZYHM14",s,s1,"2000","123456");
//        createLog("ZYHM14",s,s1,"2000","123456");
//        createLog("ZYHM14",s,s1,"2000","123456");
//        createLog("ZYHM14",s,s1,"2000","123456");
    }
}
