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
@Table(name = "sys_resources",  catalog = "agile_db")
@Remark("[系统管理]资源")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysResourcesEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    @Remark("唯一标识")
    private Integer sysResourcesId;
    @Remark("资源类型")
    private String resourceType;
    @Remark("资源名称")
    private String resourceName;
    @Remark("资源描述")
    private String resourceDesc;
    @Remark("资源路径")
    private String resourcePath;
    @Remark("优先级")
    private String priority;
    @Remark("是否可用")
    private Boolean enable;
    @Remark("是否系统权限")
    private Boolean issys;
    @Remark("模块")
    private int moduleId;

    //无参构造器
    public SysResourcesEntity(){}

    //有参构造器
    public SysResourcesEntity(Integer sysResourcesId,String resourceType,String resourceName,String resourceDesc,String resourcePath,String priority,Boolean enable,Boolean issys,int moduleId){
        this.sysResourcesId = sysResourcesId;
        this.resourceType = resourceType;
        this.resourceName = resourceName;
        this.resourceDesc = resourceDesc;
        this.resourcePath = resourcePath;
        this.priority = priority;
        this.enable = enable;
        this.issys = issys;
        this.moduleId = moduleId;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "sys_resources_id" , nullable = false )
    public Integer getSysResourcesId() {
        return sysResourcesId;
    }

    public void setSysResourcesId(int sysResourcesId) {
        this.sysResourcesId = sysResourcesId;
    }

    @Basic
    @Column(name = "resource_type" )
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Basic
    @Column(name = "resource_name" )
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Basic
    @Column(name = "resource_desc" )
    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    @Basic
    @Column(name = "resource_path" )
    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Basic
    @Column(name = "priority" )
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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
    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysResourcesEntity)) return false;
        SysResourcesEntity that = (SysResourcesEntity) object;
        return Objects.equals(getSysResourcesId(), that.getSysResourcesId()) &&
            Objects.equals(getResourceType(), that.getResourceType()) &&
            Objects.equals(getResourceName(), that.getResourceName()) &&
            Objects.equals(getResourceDesc(), that.getResourceDesc()) &&
            Objects.equals(getResourcePath(), that.getResourcePath()) &&
            Objects.equals(getPriority(), that.getPriority()) &&
            Objects.equals(getEnable(), that.getEnable()) &&
            Objects.equals(getIssys(), that.getIssys()) &&
            Objects.equals(getModuleId(), that.getModuleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysResourcesId(), getResourceType(), getResourceName(), getResourceDesc(), getResourcePath(), getPriority(), getEnable(), getIssys(), getModuleId());
    }

    @Override
    public String toString() {
        return "SysResourcesEntity{" +
        "sysResourcesId=" + sysResourcesId +
        ",resourceType='" + resourceType + '\'' +
        ",resourceName='" + resourceName + '\'' +
        ",resourceDesc='" + resourceDesc + '\'' +
        ",resourcePath='" + resourcePath + '\'' +
        ",priority='" + priority + '\'' +
        ",enable=" + enable +
        ",issys=" + issys +
        ",moduleId=" + moduleId +
        '}';
    }

    private SysResourcesEntity(Builder builder){
        this.sysResourcesId = builder.sysResourcesId;
        this.resourceType = builder.resourceType;
        this.resourceName = builder.resourceName;
        this.resourceDesc = builder.resourceDesc;
        this.resourcePath = builder.resourcePath;
        this.priority = builder.priority;
        this.enable = builder.enable;
        this.issys = builder.issys;
        this.moduleId = builder.moduleId;
    }

    public static class Builder{
        private Integer sysResourcesId;
        private String resourceType;
        private String resourceName;
        private String resourceDesc;
        private String resourcePath;
        private String priority;
        private Boolean enable;
        private Boolean issys;
        private int moduleId;

        public Builder setSysResourcesId(int sysResourcesId) {
            this.sysResourcesId = sysResourcesId;
            return this;
        }
        public Builder setResourceType(String resourceType) {
            this.resourceType = resourceType;
            return this;
        }
        public Builder setResourceName(String resourceName) {
            this.resourceName = resourceName;
            return this;
        }
        public Builder setResourceDesc(String resourceDesc) {
            this.resourceDesc = resourceDesc;
            return this;
        }
        public Builder setResourcePath(String resourcePath) {
            this.resourcePath = resourcePath;
            return this;
        }
        public Builder setPriority(String priority) {
            this.priority = priority;
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
        public Builder setModuleId(int moduleId) {
            this.moduleId = moduleId;
            return this;
        }
        public SysResourcesEntity build(){
            return new SysResourcesEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
