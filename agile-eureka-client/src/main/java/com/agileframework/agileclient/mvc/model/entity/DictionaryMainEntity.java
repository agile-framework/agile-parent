package com.agileframework.agileclient.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "dictionary_main",  catalog = "agile_db")
public class DictionaryMainEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //字典编码
    private Integer code;
    //字典名称
    private String name;
    //是否是常量
    private boolean isConstant;

    //无参构造器
    public DictionaryMainEntity(){}

    //有参构造器
    public DictionaryMainEntity(Integer code,String name,boolean isConstant){
        this.code = code;
        this.name = name;
        this.isConstant = isConstant;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "code" )
    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name" )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "is_constant" )
    public boolean getIsConstant() {
        return isConstant;
    }

    public void setIsConstant(boolean isConstant) {
        this.isConstant = isConstant;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryMainEntity that = (DictionaryMainEntity) o;

        return 
            Objects.equals(code, that.code)  && 
            (name != null ? name.equals(that.name) : that.name == null)  && 
            isConstant == that.isConstant ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isConstant ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DictionaryMainEntity{" +
        "code=" + code +
        ",name='" + name + '\'' +
        ",isConstant=" + isConstant +
        '}';
    }
}
