package com.agileframework.agileclient.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_roles",  catalog = "agile_db")
public class SysRolesEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //角色唯一标识
    private Integer sysRolesId;
    //角色名称
    private String roleName;
    //角色说明
    private String roleDesc;
    //是否可用
    private boolean enable;
    //是否系统权限
    private boolean issys;
    //模块
    private String moduleId;

    //无参构造器
    public SysRolesEntity(){}

    //有参构造器
    public SysRolesEntity(Integer sysRolesId,String roleName,String roleDesc,boolean enable,boolean issys,String moduleId){
        this.sysRolesId = sysRolesId;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.enable = enable;
        this.issys = issys;
        this.moduleId = moduleId;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "sys_roles_id" )
    public Integer getSysRolesId() {
        return sysRolesId;
    }

    public void setSysRolesId(int sysRolesId) {
        this.sysRolesId = sysRolesId;
    }

    @Basic
    @Column(name = "role_name" , nullable = false )
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_desc" , nullable = false )
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Basic
    @Column(name = "enable" , nullable = false )
    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "issys" , nullable = false )
    public boolean getIssys() {
        return issys;
    }

    public void setIssys(boolean issys) {
        this.issys = issys;
    }

    @Basic
    @Column(name = "module_id" , nullable = false )
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRolesEntity that = (SysRolesEntity) o;

        return 
            Objects.equals(sysRolesId, that.sysRolesId)  && 
            (roleName != null ? roleName.equals(that.roleName) : that.roleName == null)  && 
            (roleDesc != null ? roleDesc.equals(that.roleDesc) : that.roleDesc == null)  && 
            enable == that.enable  && 
            issys == that.issys  && 
            (moduleId != null ? moduleId.equals(that.moduleId) : that.moduleId == null) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (getSysRolesId() != null ? getSysRolesId().hashCode() : 0);
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (roleDesc != null ? roleDesc.hashCode() : 0);
        result = 31 * result + (enable ? 1 : 0);
        result = 31 * result + (issys ? 1 : 0);
        result = 31 * result + (moduleId != null ? moduleId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SysRolesEntity{" +
        "sysRolesId=" + sysRolesId +
        ",roleName='" + roleName + '\'' +
        ",roleDesc='" + roleDesc + '\'' +
        ",enable=" + enable +
        ",issys=" + issys +
        ",moduleId='" + moduleId + '\'' +
        '}';
    }
}
