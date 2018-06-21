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
@Table(name = "dictionary_main",  catalog = "agile_db")
@Remark("[系统管理]字典表")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DictionaryMainEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    @Remark("字典编码")
    private Integer code;
    @Remark("字典名称")
    private String name;
    @Remark("是否是常量")
    private Boolean isConstant;

    //无参构造器
    public DictionaryMainEntity(){}

    //有参构造器
    public DictionaryMainEntity(Integer code,String name,Boolean isConstant){
        this.code = code;
        this.name = name;
        this.isConstant = isConstant;
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
    @Column(name = "name" , nullable = false )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "is_constant" , nullable = false )
    public Boolean getIsConstant() {
        return isConstant;
    }

    public void setIsConstant(Boolean isConstant) {
        this.isConstant = isConstant;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof DictionaryMainEntity)) return false;
        DictionaryMainEntity that = (DictionaryMainEntity) object;
        return Objects.equals(getCode(), that.getCode()) &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getIsConstant(), that.getIsConstant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getName(), getIsConstant());
    }

    @Override
    public String toString() {
        return "DictionaryMainEntity{" +
        "code=" + code +
        ",name='" + name + '\'' +
        ",isConstant=" + isConstant +
        '}';
    }

    private DictionaryMainEntity(Builder builder){
        this.code = builder.code;
        this.name = builder.name;
        this.isConstant = builder.isConstant;
    }

    public static class Builder{
        private Integer code;
        private String name;
        private Boolean isConstant;

        public Builder setCode(int code) {
            this.code = code;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setIsConstant(Boolean isConstant) {
            this.isConstant = isConstant;
            return this;
        }
        public DictionaryMainEntity build(){
            return new DictionaryMainEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
