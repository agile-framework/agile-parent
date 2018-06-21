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
@Table(name = "sys_modules",  catalog = "agile_db")
@Remark("[系统管理]模块")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysModulesEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    @Remark("唯一标识")
    private Integer sysModulesId;
    @Remark("模块名称")
    private String moduleName;
    @Remark("模块说明")
    private String moduleDesc;
    @Remark("模块类型")
    private String moduleType;
    @Remark("模块上级")
    private String parent;
    @Remark("模块地址")
    private String moduleUrl;
    @Remark("菜单级别")
    private String iLevel;
    @Remark("最下级")
    private String leaf;
    @Remark("应用名称")
    private String application;
    @Remark("控制器名称")
    private String controller;
    @Remark("是否可用")
    private Boolean enable;
    @Remark("优先级")
    private String priority;

    //无参构造器
    public SysModulesEntity(){}

    //有参构造器
    public SysModulesEntity(Integer sysModulesId,String moduleName,String moduleDesc,String moduleType,String parent,String moduleUrl,String iLevel,String leaf,String application,String controller,Boolean enable,String priority){
        this.sysModulesId = sysModulesId;
        this.moduleName = moduleName;
        this.moduleDesc = moduleDesc;
        this.moduleType = moduleType;
        this.parent = parent;
        this.moduleUrl = moduleUrl;
        this.iLevel = iLevel;
        this.leaf = leaf;
        this.application = application;
        this.controller = controller;
        this.enable = enable;
        this.priority = priority;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "sys_modules_id" , nullable = false )
    public Integer getSysModulesId() {
        return sysModulesId;
    }

    public void setSysModulesId(int sysModulesId) {
        this.sysModulesId = sysModulesId;
    }

    @Basic
    @Column(name = "module_name" , nullable = false )
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Basic
    @Column(name = "module_desc" )
    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    @Basic
    @Column(name = "module_type" )
    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    @Basic
    @Column(name = "parent" )
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Basic
    @Column(name = "module_url" )
    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    @Basic
    @Column(name = "i_level" )
    public String getILevel() {
        return iLevel;
    }

    public void setILevel(String iLevel) {
        this.iLevel = iLevel;
    }

    @Basic
    @Column(name = "leaf" )
    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    @Basic
    @Column(name = "application" )
    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Basic
    @Column(name = "controller" )
    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
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
    @Column(name = "priority" )
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysModulesEntity)) return false;
        SysModulesEntity that = (SysModulesEntity) object;
        return Objects.equals(getSysModulesId(), that.getSysModulesId()) &&
            Objects.equals(getModuleName(), that.getModuleName()) &&
            Objects.equals(getModuleDesc(), that.getModuleDesc()) &&
            Objects.equals(getModuleType(), that.getModuleType()) &&
            Objects.equals(getParent(), that.getParent()) &&
            Objects.equals(getModuleUrl(), that.getModuleUrl()) &&
            Objects.equals(getILevel(), that.getILevel()) &&
            Objects.equals(getLeaf(), that.getLeaf()) &&
            Objects.equals(getApplication(), that.getApplication()) &&
            Objects.equals(getController(), that.getController()) &&
            Objects.equals(getEnable(), that.getEnable()) &&
            Objects.equals(getPriority(), that.getPriority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysModulesId(), getModuleName(), getModuleDesc(), getModuleType(), getParent(), getModuleUrl(), getILevel(), getLeaf(), getApplication(), getController(), getEnable(), getPriority());
    }

    @Override
    public String toString() {
        return "SysModulesEntity{" +
        "sysModulesId=" + sysModulesId +
        ",moduleName='" + moduleName + '\'' +
        ",moduleDesc='" + moduleDesc + '\'' +
        ",moduleType='" + moduleType + '\'' +
        ",parent='" + parent + '\'' +
        ",moduleUrl='" + moduleUrl + '\'' +
        ",iLevel='" + iLevel + '\'' +
        ",leaf='" + leaf + '\'' +
        ",application='" + application + '\'' +
        ",controller='" + controller + '\'' +
        ",enable=" + enable +
        ",priority='" + priority + '\'' +
        '}';
    }

    private SysModulesEntity(Builder builder){
        this.sysModulesId = builder.sysModulesId;
        this.moduleName = builder.moduleName;
        this.moduleDesc = builder.moduleDesc;
        this.moduleType = builder.moduleType;
        this.parent = builder.parent;
        this.moduleUrl = builder.moduleUrl;
        this.iLevel = builder.iLevel;
        this.leaf = builder.leaf;
        this.application = builder.application;
        this.controller = builder.controller;
        this.enable = builder.enable;
        this.priority = builder.priority;
    }

    public static class Builder{
        private Integer sysModulesId;
        private String moduleName;
        private String moduleDesc;
        private String moduleType;
        private String parent;
        private String moduleUrl;
        private String iLevel;
        private String leaf;
        private String application;
        private String controller;
        private Boolean enable;
        private String priority;

        public Builder setSysModulesId(int sysModulesId) {
            this.sysModulesId = sysModulesId;
            return this;
        }
        public Builder setModuleName(String moduleName) {
            this.moduleName = moduleName;
            return this;
        }
        public Builder setModuleDesc(String moduleDesc) {
            this.moduleDesc = moduleDesc;
            return this;
        }
        public Builder setModuleType(String moduleType) {
            this.moduleType = moduleType;
            return this;
        }
        public Builder setParent(String parent) {
            this.parent = parent;
            return this;
        }
        public Builder setModuleUrl(String moduleUrl) {
            this.moduleUrl = moduleUrl;
            return this;
        }
        public Builder setILevel(String iLevel) {
            this.iLevel = iLevel;
            return this;
        }
        public Builder setLeaf(String leaf) {
            this.leaf = leaf;
            return this;
        }
        public Builder setApplication(String application) {
            this.application = application;
            return this;
        }
        public Builder setController(String controller) {
            this.controller = controller;
            return this;
        }
        public Builder setEnable(Boolean enable) {
            this.enable = enable;
            return this;
        }
        public Builder setPriority(String priority) {
            this.priority = priority;
            return this;
        }
        public SysModulesEntity build(){
            return new SysModulesEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
