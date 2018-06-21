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
@Table(name = "sys_bt_authorities_resources",  catalog = "agile_db")
@Remark("[系统管理]权限资源表")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysBtAuthoritiesResourcesEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    @Remark("唯一标识")
    private Integer sysBtAuthoritiesResourcesId;
    @Remark("资源唯一标识")
    private int resourceId;
    @Remark("权限唯一标识")
    private int authorityId;

    //无参构造器
    public SysBtAuthoritiesResourcesEntity(){}

    //有参构造器
    public SysBtAuthoritiesResourcesEntity(Integer sysBtAuthoritiesResourcesId,int resourceId,int authorityId){
        this.sysBtAuthoritiesResourcesId = sysBtAuthoritiesResourcesId;
        this.resourceId = resourceId;
        this.authorityId = authorityId;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "sys_bt_authorities_resources_id" , nullable = false )
    public Integer getSysBtAuthoritiesResourcesId() {
        return sysBtAuthoritiesResourcesId;
    }

    public void setSysBtAuthoritiesResourcesId(int sysBtAuthoritiesResourcesId) {
        this.sysBtAuthoritiesResourcesId = sysBtAuthoritiesResourcesId;
    }

    @Basic
    @Column(name = "resource_id" , nullable = false )
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "authority_id" , nullable = false )
    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysBtAuthoritiesResourcesEntity)) return false;
        SysBtAuthoritiesResourcesEntity that = (SysBtAuthoritiesResourcesEntity) object;
        return Objects.equals(getSysBtAuthoritiesResourcesId(), that.getSysBtAuthoritiesResourcesId()) &&
            Objects.equals(getResourceId(), that.getResourceId()) &&
            Objects.equals(getAuthorityId(), that.getAuthorityId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysBtAuthoritiesResourcesId(), getResourceId(), getAuthorityId());
    }

    @Override
    public String toString() {
        return "SysBtAuthoritiesResourcesEntity{" +
        "sysBtAuthoritiesResourcesId=" + sysBtAuthoritiesResourcesId +
        ",resourceId=" + resourceId +
        ",authorityId=" + authorityId +
        '}';
    }

    private SysBtAuthoritiesResourcesEntity(Builder builder){
        this.sysBtAuthoritiesResourcesId = builder.sysBtAuthoritiesResourcesId;
        this.resourceId = builder.resourceId;
        this.authorityId = builder.authorityId;
    }

    public static class Builder{
        private Integer sysBtAuthoritiesResourcesId;
        private int resourceId;
        private int authorityId;

        public Builder setSysBtAuthoritiesResourcesId(int sysBtAuthoritiesResourcesId) {
            this.sysBtAuthoritiesResourcesId = sysBtAuthoritiesResourcesId;
            return this;
        }
        public Builder setResourceId(int resourceId) {
            this.resourceId = resourceId;
            return this;
        }
        public Builder setAuthorityId(int authorityId) {
            this.authorityId = authorityId;
            return this;
        }
        public SysBtAuthoritiesResourcesEntity build(){
            return new SysBtAuthoritiesResourcesEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
