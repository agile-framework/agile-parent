package com.agileframework.agileclient.common.server;

import com.agileframework.agileclient.common.base.RETURN;
import com.agileframework.agileclient.common.util.ObjectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by 佟盟 on 2018/5/29
 */
public class BusinessService<T> extends MainService {
    private Class<T> entityClass;

    public BusinessService() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
    }

    /**
     * 新增
     */
    public RETURN save() {
        T entity = ObjectUtil.getObjectFromMap(entityClass, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     */
    public RETURN delete(){
        if (this.containsKey("id")){
            String[] ids = this.getInParamOfArray("id");
            dao.deleteInBatch(entityClass,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    /**
     * 修改
     */
    public RETURN update() {
        T entity = ObjectUtil.getObjectFromMap(entityClass, this.getInParam());
        Field field = dao.getIdField(entityClass);
        field.setAccessible(true);
        try {
            if (ObjectUtil.isEmpty(field.get(entity)) && ObjectUtil.isValidity(field.get(entity))) return RETURN.PARAMETER_ERROR;
        } catch (IllegalAccessException e) {
            return RETURN.HIBERNATE_EXPRESSION;
        }
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     */
    public RETURN query() {
        this.setOutParam("queryList",dao.findAll(entityClass,getInParam("page",Integer.class,0),getInParam("size",Integer.class,10)));
        return RETURN.SUCCESS;
    }

}
