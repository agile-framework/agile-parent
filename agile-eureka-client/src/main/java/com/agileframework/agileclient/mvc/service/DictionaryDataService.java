package com.agileframework.agileclient.mvc.service;

import com.agileframework.agileclient.common.server.BusinessService;
import com.agileframework.agileclient.mvc.model.entity.DictionaryDataEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * Created by 佟盟
 */
@Service
public class DictionaryDataService extends BusinessService<DictionaryDataEntity> {
    public void asd(){
        this.setOutParam("redirect","/DictionaryDataService/da?b=2");
        setOutParam("a",2);
    }
    public String da(){
        dao.findAll("select * from dictionary_data where 1=?",1);
        System.out.println(123);
        this.setOutParam("w","111");
        return "123";
    }

    public String da1(){
        System.out.println(123);
        this.setOutParam("w","111");
        return null;
    }

    public void qwe() throws CloneNotSupportedException {
        DictionaryDataEntity a = new DictionaryDataEntity();
        DictionaryDataEntity sd = (DictionaryDataEntity) a.clone();
        this.dao.findAll("select * from dictionary_data",0,2);
        EntityManager session = this.dao.getEntityManager();
        //第一次执行get操作
        System.out.println(session.createNativeQuery("select * from dictionary_data").getResultList());
        //第二次执行get操作
        System.out.println(session.createNativeQuery("select * from dictionary_data").getResultList());
        //清除session缓存
        session.clear();

        //第三次执行get操作
        System.out.println(session.createNativeQuery("select * from dictionary_data").getResultList());
        //提交事务

    }
}
