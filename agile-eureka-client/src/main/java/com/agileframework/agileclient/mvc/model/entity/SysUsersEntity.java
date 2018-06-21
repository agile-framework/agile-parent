package com.agileframework.agileclient.mvc.model.entity;

import com.agileframework.agileclient.common.annotation.Remark;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by 佟盟
 */
@Entity
@Table(name = "sys_users",  catalog = "agile_db")
@Remark("[系统管理]用户")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUsersEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    @Remark("唯一标识")
    private Integer sysUsersId;
    @Remark("用户名")
    private String username;
    @Remark("用户姓名")
    private String name;
    @Remark("密码")
    private String password;
    @Remark("创建日期")
    private Date dtCreate;
    @Remark("最后登录日期")
    private Date lastLogin;
    @Remark("截止日期")
    private Date deadline;
    @Remark("最后登录IP地址")
    private String loginIp;
    @Remark("所属机构ID")
    private String vQzjgid;
    @Remark("所属机构名称")
    private String vQzjgmc;
    @Remark("地区编号")
    private String depId;
    @Remark("地区名称")
    private String depName;
    @Remark("是否可用")
    private Boolean enabled;
    @Remark("用户是否过期")
    private Boolean accountNonExpired;
    @Remark("用户是否锁定")
    private Boolean accountNonLocked;
    @Remark("用户证书是否有效")
    private Boolean credentialsNonExpired;

    //无参构造器
    public SysUsersEntity(){}

    //有参构造器
    public SysUsersEntity(Integer sysUsersId,String username,String name,String password,Date dtCreate,Date lastLogin,Date deadline,String loginIp,String vQzjgid,String vQzjgmc,String depId,String depName,Boolean enabled,Boolean accountNonExpired,Boolean accountNonLocked,Boolean credentialsNonExpired){
        this.sysUsersId = sysUsersId;
        this.username = username;
        this.name = name;
        this.password = password;
        this.dtCreate = dtCreate;
        this.lastLogin = lastLogin;
        this.deadline = deadline;
        this.loginIp = loginIp;
        this.vQzjgid = vQzjgid;
        this.vQzjgmc = vQzjgmc;
        this.depId = depId;
        this.depName = depName;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "sys_users_id" , nullable = false )
    public Integer getSysUsersId() {
        return sysUsersId;
    }

    public void setSysUsersId(int sysUsersId) {
        this.sysUsersId = sysUsersId;
    }

    @Basic
    @Column(name = "username" , nullable = false )
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "name" )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password" , nullable = false )
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "dt_create" )
    public Date getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Date dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Basic
    @Column(name = "last_login" )
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "deadline" )
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "login_ip" )
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Basic
    @Column(name = "v_qzjgid" )
    public String getVQzjgid() {
        return vQzjgid;
    }

    public void setVQzjgid(String vQzjgid) {
        this.vQzjgid = vQzjgid;
    }

    @Basic
    @Column(name = "v_qzjgmc" )
    public String getVQzjgmc() {
        return vQzjgmc;
    }

    public void setVQzjgmc(String vQzjgmc) {
        this.vQzjgmc = vQzjgmc;
    }

    @Basic
    @Column(name = "dep_id" )
    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Basic
    @Column(name = "dep_name" )
    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Basic
    @Column(name = "enabled" )
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "account_non_expired" )
    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Basic
    @Column(name = "account_non_locked" )
    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Basic
    @Column(name = "credentials_non_expired" )
    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysUsersEntity)) return false;
        SysUsersEntity that = (SysUsersEntity) object;
        return Objects.equals(getSysUsersId(), that.getSysUsersId()) &&
            Objects.equals(getUsername(), that.getUsername()) &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getPassword(), that.getPassword()) &&
            Objects.equals(getDtCreate(), that.getDtCreate()) &&
            Objects.equals(getLastLogin(), that.getLastLogin()) &&
            Objects.equals(getDeadline(), that.getDeadline()) &&
            Objects.equals(getLoginIp(), that.getLoginIp()) &&
            Objects.equals(getVQzjgid(), that.getVQzjgid()) &&
            Objects.equals(getVQzjgmc(), that.getVQzjgmc()) &&
            Objects.equals(getDepId(), that.getDepId()) &&
            Objects.equals(getDepName(), that.getDepName()) &&
            Objects.equals(getEnabled(), that.getEnabled()) &&
            Objects.equals(getAccountNonExpired(), that.getAccountNonExpired()) &&
            Objects.equals(getAccountNonLocked(), that.getAccountNonLocked()) &&
            Objects.equals(getCredentialsNonExpired(), that.getCredentialsNonExpired());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysUsersId(), getUsername(), getName(), getPassword(), getDtCreate(), getLastLogin(), getDeadline(), getLoginIp(), getVQzjgid(), getVQzjgmc(), getDepId(), getDepName(), getEnabled(), getAccountNonExpired(), getAccountNonLocked(), getCredentialsNonExpired());
    }

    @Override
    public String toString() {
        return "SysUsersEntity{" +
        "sysUsersId=" + sysUsersId +
        ",username='" + username + '\'' +
        ",name='" + name + '\'' +
        ",password='" + password + '\'' +
        ",dtCreate=" + dtCreate +
        ",lastLogin=" + lastLogin +
        ",deadline=" + deadline +
        ",loginIp='" + loginIp + '\'' +
        ",vQzjgid='" + vQzjgid + '\'' +
        ",vQzjgmc='" + vQzjgmc + '\'' +
        ",depId='" + depId + '\'' +
        ",depName='" + depName + '\'' +
        ",enabled=" + enabled +
        ",accountNonExpired=" + accountNonExpired +
        ",accountNonLocked=" + accountNonLocked +
        ",credentialsNonExpired=" + credentialsNonExpired +
        '}';
    }

    private SysUsersEntity(Builder builder){
        this.sysUsersId = builder.sysUsersId;
        this.username = builder.username;
        this.name = builder.name;
        this.password = builder.password;
        this.dtCreate = builder.dtCreate;
        this.lastLogin = builder.lastLogin;
        this.deadline = builder.deadline;
        this.loginIp = builder.loginIp;
        this.vQzjgid = builder.vQzjgid;
        this.vQzjgmc = builder.vQzjgmc;
        this.depId = builder.depId;
        this.depName = builder.depName;
        this.enabled = builder.enabled;
        this.accountNonExpired = builder.accountNonExpired;
        this.accountNonLocked = builder.accountNonLocked;
        this.credentialsNonExpired = builder.credentialsNonExpired;
    }

    public static class Builder{
        private Integer sysUsersId;
        private String username;
        private String name;
        private String password;
        private Date dtCreate;
        private Date lastLogin;
        private Date deadline;
        private String loginIp;
        private String vQzjgid;
        private String vQzjgmc;
        private String depId;
        private String depName;
        private Boolean enabled;
        private Boolean accountNonExpired;
        private Boolean accountNonLocked;
        private Boolean credentialsNonExpired;

        public Builder setSysUsersId(int sysUsersId) {
            this.sysUsersId = sysUsersId;
            return this;
        }
        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }
        public Builder setDtCreate(Date dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }
        public Builder setLastLogin(Date lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }
        public Builder setDeadline(Date deadline) {
            this.deadline = deadline;
            return this;
        }
        public Builder setLoginIp(String loginIp) {
            this.loginIp = loginIp;
            return this;
        }
        public Builder setVQzjgid(String vQzjgid) {
            this.vQzjgid = vQzjgid;
            return this;
        }
        public Builder setVQzjgmc(String vQzjgmc) {
            this.vQzjgmc = vQzjgmc;
            return this;
        }
        public Builder setDepId(String depId) {
            this.depId = depId;
            return this;
        }
        public Builder setDepName(String depName) {
            this.depName = depName;
            return this;
        }
        public Builder setEnabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }
        public Builder setAccountNonExpired(Boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
            return this;
        }
        public Builder setAccountNonLocked(Boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
            return this;
        }
        public Builder setCredentialsNonExpired(Boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }
        public SysUsersEntity build(){
            return new SysUsersEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
