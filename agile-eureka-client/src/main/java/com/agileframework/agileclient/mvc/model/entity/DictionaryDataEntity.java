package com.agileframework.agileclient.mvc.model.entity;

import com.agileframework.agileclient.common.annotation.Remark;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by 佟盟
 */
@Entity
@Table(name = "dictionary_data",  catalog = "agile_db")
@Remark("[系统管理]字典数据表")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DictionaryDataEntity implements Serializable,Cloneable {

    //序列
    private static final long serialVersionUID = 1L;
    @Remark("字典编码")
    private Integer code;
    @Remark("字典表_字典编码")
    private String dicCode;
    @Remark("字典值显示名称")
    private String name;
    @Remark("字典值代表值")
    private String value;
    @Remark("字典值是否固定")
    private Boolean isFixed;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //无参构造器
    public DictionaryDataEntity(){}

    //有参构造器
    public DictionaryDataEntity(Integer code,String dicCode,String name,String value,Boolean isFixed){
        this.code = code;
        this.dicCode = dicCode;
        this.name = name;
        this.value = value;
        this.isFixed = isFixed;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "code" , nullable = false )
    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "dic_code" )
    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    @Basic
    @Column(name = "name" , nullable = false )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "value" , nullable = false )
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "is_fixed" , nullable = false )
    public Boolean getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(Boolean isFixed) {
        this.isFixed = isFixed;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof DictionaryDataEntity)) return false;
        DictionaryDataEntity that = (DictionaryDataEntity) object;
        return Objects.equals(getCode(), that.getCode()) &&
            Objects.equals(getDicCode(), that.getDicCode()) &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getValue(), that.getValue()) &&
            Objects.equals(getIsFixed(), that.getIsFixed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getDicCode(), getName(), getValue(), getIsFixed());
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

    private DictionaryDataEntity(Builder builder){
        this.code = builder.code;
        this.dicCode = builder.dicCode;
        this.name = builder.name;
        this.value = builder.value;
        this.isFixed = builder.isFixed;
    }

    public static class Builder{
        private Integer code;
        private String dicCode;
        private String name;
        private String value;
        private Boolean isFixed;

        public Builder setCode(int code) {
            this.code = code;
            return this;
        }
        public Builder setDicCode(String dicCode) {
            this.dicCode = dicCode;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setValue(String value) {
            this.value = value;
            return this;
        }
        public Builder setIsFixed(Boolean isFixed) {
            this.isFixed = isFixed;
            return this;
        }
        public DictionaryDataEntity build(){
            return new DictionaryDataEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
