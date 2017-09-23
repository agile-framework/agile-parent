package com.agileframework.agileclient.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "dictionary_data",  catalog = "agile_db")
public class DictionaryDataEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //字典编码
    private Integer code;
    //字典表_字典编码
    private String dicCode;
    //字典值显示名称
    private String name;
    //字典值代表值
    private String value;
    //字典值是否固定
    private boolean isFixed;

    //无参构造器
    public DictionaryDataEntity(){}

    //有参构造器
    public DictionaryDataEntity(Integer code,String dicCode,String name,String value,boolean isFixed){
        this.code = code;
        this.dicCode = dicCode;
        this.name = name;
        this.value = value;
        this.isFixed = isFixed;
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
    @Column(name = "dic_code" , nullable = false )
    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
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
    @Column(name = "value" )
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "is_fixed" )
    public boolean getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(boolean isFixed) {
        this.isFixed = isFixed;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryDataEntity that = (DictionaryDataEntity) o;

        return 
            Objects.equals(code, that.code)  && 
            (dicCode != null ? dicCode.equals(that.dicCode) : that.dicCode == null)  && 
            (name != null ? name.equals(that.name) : that.name == null)  && 
            (value != null ? value.equals(that.value) : that.value == null)  && 
            isFixed == that.isFixed ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        result = 31 * result + (dicCode != null ? dicCode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (isFixed ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DictionaryDataEntity{" +
        "code=" + code +
        ",dicCode='" + dicCode + '\'' +
        ",name='" + name + '\'' +
        ",value='" + value + '\'' +
        ",isFixed=" + isFixed +
        '}';
    }
}
