package com.agileframework.agileclient.mvc.model.dao;

import com.agileframework.agileclient.common.exception.NoSuchIDException;
import com.agileframework.agileclient.common.util.ArrayUtil;
import com.agileframework.agileclient.common.util.ObjectUtil;
import com.agileframework.agileclient.common.util.StringUtil;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by 佟盟 on 2017/11/15
 */
@Lazy
@Component
public class Dao {
    private static Map<String,Object> map = new HashMap<>();

    @PersistenceContext
    private EntityManager entityManager ;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    private <T,ID extends Serializable> JpaRepository getRepository(Class<T> tableClass) throws NoSuchIDException {
        Field field = getIdField(tableClass);
        if(ObjectUtil.isEmpty(field)){
            throw new NoSuchIDException();
        }else {
            @SuppressWarnings("unchecked")
            Class<ID> idClass = (Class<ID>) field.getType();
            JpaRepository repository = (JpaRepository) map.get(tableClass.getName());
            if(ObjectUtil.isEmpty(repository)){
                repository = new SimpleJpaRepository<T,ID>(tableClass,entityManager);
                map.put(tableClass.getName(),repository);
            }
            return repository;
        }
    }

    /**
     * 获取EntityManager，操作jpa api的入口
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * 保存
     * @param o ORM对象
     */
    public boolean save(Object o){
        boolean isTrue = false;
        try {
            entityManager.persist(o);
            isTrue = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return isTrue;
    }

    /**
     * 保存
     * @param list ORM对象列表
     */
    public <T> boolean save(Iterable<T> list){
        boolean isTrue = false;
        Iterator<T> iterator = list.iterator();
        if(iterator.hasNext()){
            T obj = iterator.next();
            try {
                getRepository(obj.getClass()).saveAll(list);
                isTrue = true;
            } catch (NoSuchIDException e) {
                e.printStackTrace();
            }
        }
        return isTrue;
    }

    /**
     * 保存并刷新
     */
    @SuppressWarnings("unchecked")
    public <T>T saveAndReturn(T o,boolean isFlush){
        try {
            if(isFlush){
                return (T)getRepository(o.getClass()).saveAndFlush(o);
            }else {
                return (T)getRepository(o.getClass()).save(o);
            }
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存
     */
    @SuppressWarnings("unchecked")
    public <T>T saveAndReturn(T o){
        return saveAndReturn(o,Boolean.FALSE);
    }

    /**
     * 批量保存
     * @param list 要保存的ORM对象
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> saveAndReturn(Iterable<T> list){
        Iterator<T> iterator = list.iterator();
        if(iterator.hasNext()){
            T obj = iterator.next();
            try {
                getRepository(obj.getClass()).saveAll(list);
            } catch (NoSuchIDException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 判断数据是否存在
     * @param tableClass ORM对象类型
     * @param id 数据主键
     */
    @SuppressWarnings("unchecked")
    public <T>Object existsById(Class<T> tableClass,Object id){
        try {
            return getRepository(tableClass).existsById(id);
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 刷新数据库中表
     * @param tableClass ORM对象类型
     */
    @SuppressWarnings("unchecked")
    public <T>Object flush(Class<T> tableClass){
        try {
            getRepository(tableClass).flush();
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 刷新数据库中全部表
     */
    @SuppressWarnings("unchecked")
    public void flush(){
        entityManager.flush();
    }


    /**
     * 刷新数据库数据到实体类当中
     */
    @SuppressWarnings("unchecked")
    public void refresh(Object o){
        entityManager.refresh(o);
    }

    /**
     * 更新或新增
     * @param o ORM对象
     */
    public boolean update(Object o){
        boolean isTrue = false;
        try {
            entityManager.merge(o);
            isTrue = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return isTrue;
    }

    /**
     * 删除
     * @param o ORM对象
     */
    public boolean delete(Object o){
        boolean isTrue = false;
        try {
            entityManager.remove(o);
            isTrue = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return isTrue;
    }

    /**
     * 删除
     * @param tableClass ORM对象类型
     * @param id 删除的主键标识
     */
    @SuppressWarnings("unchecked")
    public <T>void deleteById(Class<T> tableClass,Object id){
        try {
            getRepository(tableClass).deleteById(id);
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除全部(逐一删除)
     * @param tableClass ORM对象类型
     */
    @SuppressWarnings("unchecked")
    public <T>void deleteAll(Class<T> tableClass){
        try {
            getRepository(tableClass).deleteAll();
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除全部(一次性删除)
     * @param tableClass ORM对象类型
     */
    @SuppressWarnings("unchecked")
    public <T>void deleteAllInBatch(Class<T> tableClass){
        try {
            getRepository(tableClass).deleteAllInBatch();
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
    }

    /**
     * 主键数组转换ORM对象列表
     * @param tableClass ORM类型
     * @param ids 主键数组
     */
    private <T,ID>List createObjectList(Class<T> tableClass,ID[] ids){
        ArrayList list = new ArrayList();
        Field idField = getIdField(tableClass);
        if(ObjectUtil.isEmpty(idField)){
            new NoSuchIDException().printStackTrace();
            return null;
        }
        for (int i = 0 ; i < ids.length;i++){
            try {
                Object instance = tableClass.newInstance();
                idField.setAccessible(true);
                idField.set(instance, ObjectUtil.cast(idField.getType(),ids[i]));
                list.add(instance);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    /**
     * 获取ORM中的主键字段
     * @param clazz ORM类
     */
    public Field getIdField(Class clazz){
        Method[] methods =  clazz.getDeclaredMethods();
        for (int i = 0 ; i < methods.length;i++){
            Method method = methods[i];
            method.setAccessible(true);
            Id id = method.getAnnotation(Id.class);
            if(ObjectUtil.isEmpty(id))continue;
            try {
                return clazz.getDeclaredField(StringUtil.toLowerName(method.getName().replaceFirst("get","")));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 部分删除
     * @param tableClass ORM对象类型
     */
    @SuppressWarnings("unchecked")
    public <T,ID>void deleteAll(Class<T> tableClass,ID[] ids){
        if(ArrayUtil.isEmpty(ids) || ids.length<1)return;
        List list = createObjectList(tableClass,ids);
        if(!ObjectUtil.isEmpty(list) && list.size()>0){
            try {
                getRepository(tableClass).deleteAll(list);
            } catch (NoSuchIDException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 部分删除(一次性删除)
     * @param tableClass ORM对象类型
     */
    @SuppressWarnings("unchecked")
    public <T,ID>void deleteInBatch(Class<T> tableClass,ID[] ids){
        if(ArrayUtil.isEmpty(ids) || ids.length<1)return;
        List list = createObjectList(tableClass, ids);
        if(!ObjectUtil.isEmpty(list) && list.size()>0){
            try {
                getRepository(tableClass).deleteInBatch(list);
            } catch (NoSuchIDException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 部分删除(一次性删除)
     * @param list 需要删除的对象列表
     */
    @SuppressWarnings("unchecked")
    public <T>void deleteInBatch(Iterable<T> list){
        Iterator<T> iterator = list.iterator();
        if(iterator.hasNext()){
            T obj = iterator.next();
            try {
                getRepository(obj.getClass()).deleteInBatch(list);
            } catch (NoSuchIDException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询单条
     */
    public <T>T findOne(Class<T> clazz,Object id){
        return entityManager.find(clazz,id);
    }

    /**
     * 按照例子查询单条
     */
    public <T>T findOne(T object){
        try {
            Example<T> example = Example.of(object);
            return (T) this.getRepository(object.getClass()).findOne(example);
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询列表
     * @param sql sql
     * @param clazz 返回ORM类型列表
     */
    @SuppressWarnings("unchecked")
    public <T>T findOne(String sql, Class<T> clazz,Object... parameters){
        Query query = entityManager.createNativeQuery(sql, clazz);
        for (int i = 0 ; i < parameters.length ; i++ ){
            query.setParameter(i+1,parameters[i]);
        }
        List list = query.getResultList();
        if (ObjectUtil.isEmpty(list) || list.size()<1)return null;
        return (T)query.getSingleResult();
    }

    /**
     * 按照例子查询多条
     */
    public <T>List<T> findAll(T object){
        try {
            Example<T> example = Example.of(object);
            return this.getRepository(object.getClass()).findAll(example);
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 按照例子查询多条分页
     */
    public <T> Page findAll(T object, int page, int size){
        try {
            Example<T> example = Example.of(object);
            return this.getRepository(object.getClass()).findAll(example, PageRequest.of(page,size));
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询列表
     * @param sql sql
     * @param clazz 返回ORM类型列表
     */
    @SuppressWarnings("unchecked")
    public <T>List<T> findAll(String sql, Class<T> clazz,Object... parameters){
        Query query = entityManager.createNativeQuery(sql, clazz);
        for (int i = 0 ; i < parameters.length ; i++ ){
            query.setParameter(i+1,parameters[i]);
        }
        return query.getResultList();
    }

    /**
     * 查询列表
     * @param sql sql
     */
    @SuppressWarnings("unchecked")
    public Page findAll(String sql, int page, int size){
        return this.findAll( sql, page, size,new Object[]{});
    }
    /**
     * 查询列表
     * @param sql sql
     */
    @SuppressWarnings("unchecked")
    public Page findAll(String sql, int page, int size, Object... parameters){
        if(size<=0){
            new IllegalArgumentException().printStackTrace();
            return null;
        }
        PageImpl pageDate = null;
        Sort sort = null;
        PageRequest pageable;

        //sql格式化
        sql = sql.trim().toLowerCase().replaceAll("[\t\r\n\\s]"," ");

        //取排序
        String[] orders = StringUtil.getMatchedString("(order by)(\\s)([\\S]+)(\\s)?(desc|asc)?",sql);
        if(!ObjectUtil.isEmpty(orders)){
            String order = ArrayUtil.getLast(orders).toString();
            String[] sortMsg = StringUtil.getGroupString("(order by)(\\s)([\\S]+)(\\s)?(desc|asc)?", order);
            if(!ObjectUtil.isEmpty(sortMsg) && sortMsg.length>2){
                Sort.Direction direction;
                if(sortMsg.length>4 && !StringUtil.isEmpty(sortMsg[4])){
                    switch (sortMsg[4]){
                        case "desc":
                            direction = Sort.Direction.DESC;
                            break;
                        default:
                            direction = Sort.Direction.ASC;
                            break;
                    }
                }else {
                    direction = Sort.Direction.ASC;
                }
                sort = new Sort(direction,sortMsg[2].replaceAll("[\\s`]",""));
            }
        }
        pageable = PageRequest.of(page,size,sort);

        //按照union分段，计算total总数
        String[] sqls = sql.split("union all|union");
        String[] unions = StringUtil.getMatchedString("(union)[\\s\\S](?:all)?",sql);

        StringBuilder sb = new StringBuilder("select sum(count_table.count) as count from (");
        for(int i = 0 ; i < sqls.length;i++){
            String countSon = sqls[i].replaceAll("(select)([\\s\\S]+)(?=from)", "select count(*) count ").replaceAll("(order)([\\s\\S]+)(?:by)([\\s\\S]+)", "");
            sb.append(countSon).append(" ");
            if(i<sqls.length-1){
                sb.append(unions[i]).append(" ");
            }
        }
        sb.append(" ) as count_table");
        Query countQuery = entityManager.createNativeQuery(sb.toString());
        int count = Integer.parseInt(countQuery.getSingleResult().toString());

        //取查询结果集
        if(count>0){
            Query query = entityManager.createNativeQuery(sql);
            for (int i = 0 ; i < parameters.length ; i++ ){
                query.setParameter(i+1,parameters[i]);
            }
            query.setFirstResult(page*size);
            query.setMaxResults(size);
            ((NativeQueryImpl) query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List content = query.getResultList();
            pageDate = new PageImpl(content,pageable,count);
        }

        return pageDate;
    }

    /**
     * 查询列表
     * @param sql sql
     */
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> findAll(String sql,Object... parameters){
        Query query = entityManager.createNativeQuery(sql);
        for (int i = 0 ; i < parameters.length ; i++ ){
            query.setParameter(i+1,parameters[i]);
        }
        ((NativeQueryImpl) query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    /**
     * 查询属性
     * sql查询结果必须只包含一个字段
     * @param sql sql
     */
    @SuppressWarnings("unchecked")
    public Object findParameter(String sql,Object... parameters){
        Query query = entityManager.createNativeQuery(sql);
        for (int i = 0 ; i < parameters.length ; i++ ){
            query.setParameter(i+1,parameters[i]);
        }
        return query.getSingleResult();
    }

    /**
     * 查询列表
     */
    @SuppressWarnings("unchecked")
    public <T,ID>List<T> findAll(Class<T> tableClass,Iterable<ID> ids){
        try {
            return getRepository(tableClass).findAllById(ids);
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询列表
     */
    @SuppressWarnings("unchecked")
    public <T,ID>List<T> findAll(Class<T> tableClass,ID[] ids){
        try {
            return getRepository(tableClass).findAllById(ArrayUtil.asList(ids));
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询列表
     */
    @SuppressWarnings("unchecked")
    public <T> Page<T> findAll(Class<T> tableClass, int page, int size){
        try {
            return getRepository(tableClass).findAll(PageRequest.of(page,size));
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询列表
     */
    @SuppressWarnings("unchecked")
    public <T>List<T> findAll(Class<T> tableClass){
        try {
            return getRepository(tableClass).findAll();
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询表总数
     */
    @SuppressWarnings("unchecked")
    public long count(Class tableClass){
        try {
            return getRepository(tableClass).count();
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
