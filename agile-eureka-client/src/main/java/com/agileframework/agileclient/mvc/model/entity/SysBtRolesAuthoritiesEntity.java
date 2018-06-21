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
@Table(name = "sys_bt_roles_authorities",  catalog = "agile_db")
@Remark("[系统管理]角色权限表")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysBtRolesAuthoritiesEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    @Remark("唯一标识")
    private Integer sysBtRolesAuthoritiesId;
    @Remark("权限唯一标识")
    private int authorityId;
    @Remark("角色唯一标识")
    private int roleId;

    //无参构造器
    public SysBtRolesAuthoritiesEntity(){}

    //有参构造器
    public SysBtRolesAuthoritiesEntity(Integer sysBtRolesAuthoritiesId,int authorityId,int roleId){
        this.sysBtRolesAuthoritiesId = sysBtRolesAuthoritiesId;
        this.authorityId = authorityId;
        this.roleId = roleId;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "sys_bt_roles_authorities_id" , nullable = false )
    public Integer getSysBtRolesAuthoritiesId() {
        return sysBtRolesAuthoritiesId;
    }

    public void setSysBtRolesAuthoritiesId(int sysBtRolesAuthoritiesId) {
        this.sysBtRolesAuthoritiesId = sysBtRolesAuthoritiesId;
    }

    @Basic
    @Column(name = "authority_id" , nullable = false )
    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Basic
    @Column(name = "role_id" , nullable = false )
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysBtRolesAuthoritiesEntity)) return false;
        SysBtRolesAuthoritiesEntity that = (SysBtRolesAuthoritiesEntity) object;
        return Objects.equals(getSysBtRolesAuthoritiesId(), that.getSysBtRolesAuthoritiesId()) &&
            Objects.equals(getAuthorityId(), that.getAuthorityId()) &&
            Objects.equals(getRoleId(), that.getRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysBtRolesAuthoritiesId(), getAuthorityId(), getRoleId());
    }

    @Override
    public String toString() {
        return "SysBtRolesAuthoritiesEntity{" +
        "sysBtRolesAuthoritiesId=" + sysBtRolesAuthoritiesId +
        ",authorityId=" + authorityId +
        ",roleId=" + roleId +
        '}';
    }

    private SysBtRolesAuthoritiesEntity(Builder builder){
        this.sysBtRolesAuthoritiesId = builder.sysBtRolesAuthoritiesId;
        this.authorityId = builder.authorityId;
        this.roleId = builder.roleId;
    }

    public static class Builder{
        private Integer sysBtRolesAuthoritiesId;
        private int authorityId;
        private int roleId;

        public Builder setSysBtRolesAuthoritiesId(int sysBtRolesAuthoritiesId) {
            this.sysBtRolesAuthoritiesId = sysBtRolesAuthoritiesId;
            return this;
        }
        public Builder setAuthorityId(int authorityId) {
            this.authorityId = authorityId;
            return this;
        }
        public Builder setRoleId(int roleId) {
            this.roleId = roleId;
            return this;
        }
        public SysBtRolesAuthoritiesEntity build(){
            return new SysBtRolesAuthoritiesEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
