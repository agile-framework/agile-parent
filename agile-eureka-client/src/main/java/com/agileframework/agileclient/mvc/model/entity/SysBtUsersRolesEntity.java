package com.agileframework.agileclient.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_bt_users_roles",  catalog = "agile_db")
public class SysBtUsersRolesEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private Integer sysBtUsersRolesId;
    //角色唯一标识
    private int roleId;
    //用户唯一标识
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
    @Column(name = "sys_bt_users_roles_id" )
    public Integer getSysBtUsersRolesId() {
        return sysBtUsersRolesId;
    }

    public void setSysBtUsersRolesId(int sysBtUsersRolesId) {
        this.sysBtUsersRolesId = sysBtUsersRolesId;
    }

    @Basic
    @Column(name = "role_id" )
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "user_id" )
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysBtUsersRolesEntity that = (SysBtUsersRolesEntity) o;

        return 
            Objects.equals(sysBtUsersRolesId, that.sysBtUsersRolesId)  && 
            Objects.equals(roleId, that.roleId)  && 
            Objects.equals(userId, that.userId) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (getSysBtUsersRolesId() != null ? getSysBtUsersRolesId().hashCode() : 0);
        result = 31 * result + roleId;
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return "SysBtUsersRolesEntity{" +
        "sysBtUsersRolesId=" + sysBtUsersRolesId +
        ",roleId=" + roleId +
        ",userId=" + userId +
        '}';
    }
}
