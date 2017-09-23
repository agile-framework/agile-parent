package com.agileframework.agileclient.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_modules",  catalog = "agile_db")
public class SysModulesEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private Integer sysModulesId;
    //模块名称
    private String moduleName;
    //模块说明
    private String moduleDesc;
    //模块类型
    private String moduleType;
    //模块上级
    private String parent;
    //模块地址
    private String moduleUrl;
    //菜单级别
    private String iLevel;
    //最下级
    private String leaf;
    //应用名称
    private String application;
    //控制器名称
    private String controller;
    //是否可用
    private boolean enable;
    //优先级
    private String priority;

    //无参构造器
    public SysModulesEntity(){}

    //有参构造器
    public SysModulesEntity(Integer sysModulesId,String moduleName,String moduleDesc,String moduleType,String parent,String moduleUrl,String iLevel,String leaf,String application,String controller,boolean enable,String priority){
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
    @Column(name = "sys_modules_id" )
    public Integer getSysModulesId() {
        return sysModulesId;
    }

    public void setSysModulesId(int sysModulesId) {
        this.sysModulesId = sysModulesId;
    }

    @Basic
    @Column(name = "module_name" )
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Basic
    @Column(name = "module_desc" , nullable = false )
    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    @Basic
    @Column(name = "module_type" , nullable = false )
    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    @Basic
    @Column(name = "parent" , nullable = false )
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Basic
    @Column(name = "module_url" , nullable = false )
    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    @Basic
    @Column(name = "i_level" , nullable = false )
    public String getILevel() {
        return iLevel;
    }

    public void setILevel(String iLevel) {
        this.iLevel = iLevel;
    }

    @Basic
    @Column(name = "leaf" , nullable = false )
    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    @Basic
    @Column(name = "application" , nullable = false )
    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Basic
    @Column(name = "controller" , nullable = false )
    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
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
    @Column(name = "priority" , nullable = false )
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysModulesEntity that = (SysModulesEntity) o;

        return 
            Objects.equals(sysModulesId, that.sysModulesId)  && 
            (moduleName != null ? moduleName.equals(that.moduleName) : that.moduleName == null)  && 
            (moduleDesc != null ? moduleDesc.equals(that.moduleDesc) : that.moduleDesc == null)  && 
            (moduleType != null ? moduleType.equals(that.moduleType) : that.moduleType == null)  && 
            (parent != null ? parent.equals(that.parent) : that.parent == null)  && 
            (moduleUrl != null ? moduleUrl.equals(that.moduleUrl) : that.moduleUrl == null)  && 
            (iLevel != null ? iLevel.equals(that.iLevel) : that.iLevel == null)  && 
            (leaf != null ? leaf.equals(that.leaf) : that.leaf == null)  && 
            (application != null ? application.equals(that.application) : that.application == null)  && 
            (controller != null ? controller.equals(that.controller) : that.controller == null)  && 
            enable == that.enable  && 
            (priority != null ? priority.equals(that.priority) : that.priority == null) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (getSysModulesId() != null ? getSysModulesId().hashCode() : 0);
        result = 31 * result + (moduleName != null ? moduleName.hashCode() : 0);
        result = 31 * result + (moduleDesc != null ? moduleDesc.hashCode() : 0);
        result = 31 * result + (moduleType != null ? moduleType.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (moduleUrl != null ? moduleUrl.hashCode() : 0);
        result = 31 * result + (iLevel != null ? iLevel.hashCode() : 0);
        result = 31 * result + (leaf != null ? leaf.hashCode() : 0);
        result = 31 * result + (application != null ? application.hashCode() : 0);
        result = 31 * result + (controller != null ? controller.hashCode() : 0);
        result = 31 * result + (enable ? 1 : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        return result;
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
}
