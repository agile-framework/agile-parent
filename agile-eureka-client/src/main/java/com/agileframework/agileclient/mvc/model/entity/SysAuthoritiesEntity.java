package com.agileframework.agileclient.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_authorities",  catalog = "agile_db")
public class SysAuthoritiesEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private Integer sysAuthorityId;
    //权限标识
    private String authorityMark;
    //权限名称
    private String authorityName;
    //权限说明
    private String authorityDesc;
    //提示信息
    private String message;
    //是否可用
    private boolean enable;
    //是否系统权限
    private boolean issys;
    //模块
    private String moduleId;

    //无参构造器
    public SysAuthoritiesEntity(){}

    //有参构造器
    public SysAuthoritiesEntity(Integer sysAuthorityId,String authorityMark,String authorityName,String authorityDesc,String message,boolean enable,boolean issys,String moduleId){
        this.sysAuthorityId = sysAuthorityId;
        this.authorityMark = authorityMark;
        this.authorityName = authorityName;
        this.authorityDesc = authorityDesc;
        this.message = message;
        this.enable = enable;
        this.issys = issys;
        this.moduleId = moduleId;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "sys_authority_id" )
    public Integer getSysAuthorityId() {
        return sysAuthorityId;
    }

    public void setSysAuthorityId(int sysAuthorityId) {
        this.sysAuthorityId = sysAuthorityId;
    }

    @Basic
    @Column(name = "authority_mark" , nullable = false )
    public String getAuthorityMark() {
        return authorityMark;
    }

    public void setAuthorityMark(String authorityMark) {
        this.authorityMark = authorityMark;
    }

    @Basic
    @Column(name = "authority_name" )
    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Basic
    @Column(name = "authority_desc" , nullable = false )
    public String getAuthorityDesc() {
        return authorityDesc;
    }

    public void setAuthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc;
    }

    @Basic
    @Column(name = "message" , nullable = false )
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

        SysAuthoritiesEntity that = (SysAuthoritiesEntity) o;

        return 
            Objects.equals(sysAuthorityId, that.sysAuthorityId)  && 
            (authorityMark != null ? authorityMark.equals(that.authorityMark) : that.authorityMark == null)  && 
            (authorityName != null ? authorityName.equals(that.authorityName) : that.authorityName == null)  && 
            (authorityDesc != null ? authorityDesc.equals(that.authorityDesc) : that.authorityDesc == null)  && 
            (message != null ? message.equals(that.message) : that.message == null)  && 
            enable == that.enable  && 
            issys == that.issys  && 
            (moduleId != null ? moduleId.equals(that.moduleId) : that.moduleId == null) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (getSysAuthorityId() != null ? getSysAuthorityId().hashCode() : 0);
        result = 31 * result + (authorityMark != null ? authorityMark.hashCode() : 0);
        result = 31 * result + (authorityName != null ? authorityName.hashCode() : 0);
        result = 31 * result + (authorityDesc != null ? authorityDesc.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (enable ? 1 : 0);
        result = 31 * result + (issys ? 1 : 0);
        result = 31 * result + (moduleId != null ? moduleId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SysAuthoritiesEntity{" +
        "sysAuthorityId=" + sysAuthorityId +
        ",authorityMark='" + authorityMark + '\'' +
        ",authorityName='" + authorityName + '\'' +
        ",authorityDesc='" + authorityDesc + '\'' +
        ",message='" + message + '\'' +
        ",enable=" + enable +
        ",issys=" + issys +
        ",moduleId='" + moduleId + '\'' +
        '}';
    }
}
