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
@Table(name = "sys_authorities",  catalog = "agile_db")
@Remark("[系统管理]权限")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysAuthoritiesEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    @Remark("唯一标识")
    private Integer sysAuthorityId;
    @Remark("权限标识")
    private String authorityMark;
    @Remark("权限名称")
    private String authorityName;
    @Remark("权限说明")
    private String authorityDesc;
    @Remark("提示信息")
    private String message;
    @Remark("是否可用")
    private Boolean enable;
    @Remark("是否系统权限")
    private Boolean issys;
    @Remark("模块")
    private String moduleId;

    //无参构造器
    public SysAuthoritiesEntity(){}

    //有参构造器
    public SysAuthoritiesEntity(Integer sysAuthorityId,String authorityMark,String authorityName,String authorityDesc,String message,Boolean enable,Boolean issys,String moduleId){
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
    @Column(name = "sys_authority_id" , nullable = false )
    public Integer getSysAuthorityId() {
        return sysAuthorityId;
    }

    public void setSysAuthorityId(int sysAuthorityId) {
        this.sysAuthorityId = sysAuthorityId;
    }

    @Basic
    @Column(name = "authority_mark" )
    public String getAuthorityMark() {
        return authorityMark;
    }

    public void setAuthorityMark(String authorityMark) {
        this.authorityMark = authorityMark;
    }

    @Basic
    @Column(name = "authority_name" , nullable = false )
    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Basic
    @Column(name = "authority_desc" )
    public String getAuthorityDesc() {
        return authorityDesc;
    }

    public void setAuthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc;
    }

    @Basic
    @Column(name = "message" )
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "enable" )
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "issys" )
    public Boolean getIssys() {
        return issys;
    }

    public void setIssys(Boolean issys) {
        this.issys = issys;
    }

    @Basic
    @Column(name = "module_id" )
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysAuthoritiesEntity)) return false;
        SysAuthoritiesEntity that = (SysAuthoritiesEntity) object;
        return Objects.equals(getSysAuthorityId(), that.getSysAuthorityId()) &&
            Objects.equals(getAuthorityMark(), that.getAuthorityMark()) &&
            Objects.equals(getAuthorityName(), that.getAuthorityName()) &&
            Objects.equals(getAuthorityDesc(), that.getAuthorityDesc()) &&
            Objects.equals(getMessage(), that.getMessage()) &&
            Objects.equals(getEnable(), that.getEnable()) &&
            Objects.equals(getIssys(), that.getIssys()) &&
            Objects.equals(getModuleId(), that.getModuleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysAuthorityId(), getAuthorityMark(), getAuthorityName(), getAuthorityDesc(), getMessage(), getEnable(), getIssys(), getModuleId());
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

    private SysAuthoritiesEntity(Builder builder){
        this.sysAuthorityId = builder.sysAuthorityId;
        this.authorityMark = builder.authorityMark;
        this.authorityName = builder.authorityName;
        this.authorityDesc = builder.authorityDesc;
        this.message = builder.message;
        this.enable = builder.enable;
        this.issys = builder.issys;
        this.moduleId = builder.moduleId;
    }

    public static class Builder{
        private Integer sysAuthorityId;
        private String authorityMark;
        private String authorityName;
        private String authorityDesc;
        private String message;
        private Boolean enable;
        private Boolean issys;
        private String moduleId;

        public Builder setSysAuthorityId(int sysAuthorityId) {
            this.sysAuthorityId = sysAuthorityId;
            return this;
        }
        public Builder setAuthorityMark(String authorityMark) {
            this.authorityMark = authorityMark;
            return this;
        }
        public Builder setAuthorityName(String authorityName) {
            this.authorityName = authorityName;
            return this;
        }
        public Builder setAuthorityDesc(String authorityDesc) {
            this.authorityDesc = authorityDesc;
            return this;
        }
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        public Builder setEnable(Boolean enable) {
            this.enable = enable;
            return this;
        }
        public Builder setIssys(Boolean issys) {
            this.issys = issys;
            return this;
        }
        public Builder setModuleId(String moduleId) {
            this.moduleId = moduleId;
            return this;
        }
        public SysAuthoritiesEntity build(){
            return new SysAuthoritiesEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
