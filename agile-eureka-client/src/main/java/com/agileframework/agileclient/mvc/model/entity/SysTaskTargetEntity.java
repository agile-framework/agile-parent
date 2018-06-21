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
@Table(name = "sys_task_target",  catalog = "agile_db")
@Remark("[系统管理]目标任务表")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysTaskTargetEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    @Remark("唯一标识")
    private String sysTaskTargetId;
    @Remark("方法含义名")
    private String name;
    @Remark("包名")
    private String targetPackage;
    @Remark("类名")
    private String targetClass;
    @Remark("方法名")
    private String targetMethod;
    @Remark("备注")
    private String remarks;

    //无参构造器
    public SysTaskTargetEntity(){}

    //有参构造器
    public SysTaskTargetEntity(String sysTaskTargetId,String name,String targetPackage,String targetClass,String targetMethod,String remarks){
        this.sysTaskTargetId = sysTaskTargetId;
        this.name = name;
        this.targetPackage = targetPackage;
        this.targetClass = targetClass;
        this.targetMethod = targetMethod;
        this.remarks = remarks;
    }

    @Id
    @Column(name = "sys_task_target_id" , nullable = false )
    public String getSysTaskTargetId() {
        return sysTaskTargetId;
    }

    public void setSysTaskTargetId(String sysTaskTargetId) {
        this.sysTaskTargetId = sysTaskTargetId;
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
    @Column(name = "target_package" , nullable = false )
    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    @Basic
    @Column(name = "target_class" , nullable = false )
    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    @Basic
    @Column(name = "target_method" , nullable = false )
    public String getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    @Basic
    @Column(name = "remarks" )
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysTaskTargetEntity)) return false;
        SysTaskTargetEntity that = (SysTaskTargetEntity) object;
        return Objects.equals(getSysTaskTargetId(), that.getSysTaskTargetId()) &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getTargetPackage(), that.getTargetPackage()) &&
            Objects.equals(getTargetClass(), that.getTargetClass()) &&
            Objects.equals(getTargetMethod(), that.getTargetMethod()) &&
            Objects.equals(getRemarks(), that.getRemarks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysTaskTargetId(), getName(), getTargetPackage(), getTargetClass(), getTargetMethod(), getRemarks());
    }

    @Override
    public String toString() {
        return "SysTaskTargetEntity{" +
        "sysTaskTargetId='" + sysTaskTargetId + '\'' +
        ",name='" + name + '\'' +
        ",targetPackage='" + targetPackage + '\'' +
        ",targetClass='" + targetClass + '\'' +
        ",targetMethod='" + targetMethod + '\'' +
        ",remarks='" + remarks + '\'' +
        '}';
    }

    private SysTaskTargetEntity(Builder builder){
        this.sysTaskTargetId = builder.sysTaskTargetId;
        this.name = builder.name;
        this.targetPackage = builder.targetPackage;
        this.targetClass = builder.targetClass;
        this.targetMethod = builder.targetMethod;
        this.remarks = builder.remarks;
    }

    public static class Builder{
        private String sysTaskTargetId;
        private String name;
        private String targetPackage;
        private String targetClass;
        private String targetMethod;
        private String remarks;

        public Builder setSysTaskTargetId(String sysTaskTargetId) {
            this.sysTaskTargetId = sysTaskTargetId;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setTargetPackage(String targetPackage) {
            this.targetPackage = targetPackage;
            return this;
        }
        public Builder setTargetClass(String targetClass) {
            this.targetClass = targetClass;
            return this;
        }
        public Builder setTargetMethod(String targetMethod) {
            this.targetMethod = targetMethod;
            return this;
        }
        public Builder setRemarks(String remarks) {
            this.remarks = remarks;
            return this;
        }
        public SysTaskTargetEntity build(){
            return new SysTaskTargetEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
