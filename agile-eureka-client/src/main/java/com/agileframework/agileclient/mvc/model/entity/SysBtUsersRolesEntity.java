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
@Table(name = "sys_bt_users_roles",  catalog = "agile_db")
@Remark("[系统管理]用户角色表")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysBtUsersRolesEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    @Remark("唯一标识")
    private Integer sysBtUsersRolesId;
    @Remark("角色唯一标识")
    private int roleId;
    @Remark("用户唯一标识")
    private int userId;

    //无参构造器
    public SysBtUsersRolesEntity(){}

    //有参构造器
    public SysBtUsersRolesEntity(Integer sysBtUsersRolesId,int roleId,int userId){
        this.sysBtUsersRolesId = sysBtUsersRolesId;
        this.roleId = roleId;
        this.userId = userId;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "sys_bt_users_roles_id" , nullable = false )
    public Integer getSysBtUsersRolesId() {
        return sysBtUsersRolesId;
    }

    public void setSysBtUsersRolesId(int sysBtUsersRolesId) {
        this.sysBtUsersRolesId = sysBtUsersRolesId;
    }

    @Basic
    @Column(name = "role_id" , nullable = false )
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "user_id" , nullable = false )
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysBtUsersRolesEntity)) return false;
        SysBtUsersRolesEntity that = (SysBtUsersRolesEntity) object;
        return Objects.equals(getSysBtUsersRolesId(), that.getSysBtUsersRolesId()) &&
            Objects.equals(getRoleId(), that.getRoleId()) &&
            Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysBtUsersRolesId(), getRoleId(), getUserId());
    }

    @Override
    public String toString() {
        return "SysBtUsersRolesEntity{" +
        "sysBtUsersRolesId=" + sysBtUsersRolesId +
        ",roleId=" + roleId +
        ",userId=" + userId +
        '}';
    }

    private SysBtUsersRolesEntity(Builder builder){
        this.sysBtUsersRolesId = builder.sysBtUsersRolesId;
        this.roleId = builder.roleId;
        this.userId = builder.userId;
    }

    public static class Builder{
        private Integer sysBtUsersRolesId;
        private int roleId;
        private int userId;

        public Builder setSysBtUsersRolesId(int sysBtUsersRolesId) {
            this.sysBtUsersRolesId = sysBtUsersRolesId;
            return this;
        }
        public Builder setRoleId(int roleId) {
            this.roleId = roleId;
            return this;
        }
        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }
        public SysBtUsersRolesEntity build(){
            return new SysBtUsersRolesEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
