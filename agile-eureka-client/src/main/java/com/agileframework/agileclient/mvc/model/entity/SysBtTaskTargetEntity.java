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
@Table(name = "sys_bt_task_target",  catalog = "agile_db")
@Remark("[系统管理]定时任务目标任务表")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysBtTaskTargetEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    @Remark("主键")
    private Integer sysBtTaskTargetId;
    @Remark("定时任务标志")
    private int sysTaskId;
    @Remark("目标方法主键")
    private String sysTaskTargetId;
    @Remark("优先级")
    private String order;

    //无参构造器
    public SysBtTaskTargetEntity(){}

    //有参构造器
    public SysBtTaskTargetEntity(Integer sysBtTaskTargetId,int sysTaskId,String sysTaskTargetId,String order){
        this.sysBtTaskTargetId = sysBtTaskTargetId;
        this.sysTaskId = sysTaskId;
        this.sysTaskTargetId = sysTaskTargetId;
        this.order = order;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "sys_bt_task_target_id" , nullable = false )
    public Integer getSysBtTaskTargetId() {
        return sysBtTaskTargetId;
    }

    public void setSysBtTaskTargetId(int sysBtTaskTargetId) {
        this.sysBtTaskTargetId = sysBtTaskTargetId;
    }

    @Basic
    @Column(name = "sys_task_id" , nullable = false )
    public int getSysTaskId() {
        return sysTaskId;
    }

    public void setSysTaskId(int sysTaskId) {
        this.sysTaskId = sysTaskId;
    }

    @Basic
    @Column(name = "sys_task_target_id" , nullable = false )
    public String getSysTaskTargetId() {
        return sysTaskTargetId;
    }

    public void setSysTaskTargetId(String sysTaskTargetId) {
        this.sysTaskTargetId = sysTaskTargetId;
    }

    @Basic
    @Column(name = "order" , nullable = false )
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysBtTaskTargetEntity)) return false;
        SysBtTaskTargetEntity that = (SysBtTaskTargetEntity) object;
        return Objects.equals(getSysBtTaskTargetId(), that.getSysBtTaskTargetId()) &&
            Objects.equals(getSysTaskId(), that.getSysTaskId()) &&
            Objects.equals(getSysTaskTargetId(), that.getSysTaskTargetId()) &&
            Objects.equals(getOrder(), that.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysBtTaskTargetId(), getSysTaskId(), getSysTaskTargetId(), getOrder());
    }

    @Override
    public String toString() {
        return "SysBtTaskTargetEntity{" +
        "sysBtTaskTargetId=" + sysBtTaskTargetId +
        ",sysTaskId=" + sysTaskId +
        ",sysTaskTargetId='" + sysTaskTargetId + '\'' +
        ",order='" + order + '\'' +
        '}';
    }

    private SysBtTaskTargetEntity(Builder builder){
        this.sysBtTaskTargetId = builder.sysBtTaskTargetId;
        this.sysTaskId = builder.sysTaskId;
        this.sysTaskTargetId = builder.sysTaskTargetId;
        this.order = builder.order;
    }

    public static class Builder{
        private Integer sysBtTaskTargetId;
        private int sysTaskId;
        private String sysTaskTargetId;
        private String order;

        public Builder setSysBtTaskTargetId(int sysBtTaskTargetId) {
            this.sysBtTaskTargetId = sysBtTaskTargetId;
            return this;
        }
        public Builder setSysTaskId(int sysTaskId) {
            this.sysTaskId = sysTaskId;
            return this;
        }
        public Builder setSysTaskTargetId(String sysTaskTargetId) {
            this.sysTaskTargetId = sysTaskTargetId;
            return this;
        }
        public Builder setOrder(String order) {
            this.order = order;
            return this;
        }
        public SysBtTaskTargetEntity build(){
            return new SysBtTaskTargetEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
