package com.agileframework.agileclient.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_users",  catalog = "agile_db")
public class SysUsersEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private Integer sysUsersId;
    //用户名
    private String username;
    //用户姓名
    private String name;
    //密码
    private String password;
    //创建日期
    private Date dtCreate;
    //最后登录日期
    private Date lastLogin;
    //截止日期
    private Date deadline;
    //最后登录IP地址
    private String loginIp;
    //所属机构ID
    private String vQzjgid;
    //所属机构名称
    private String vQzjgmc;
    //地区编号
    private String depId;
    //地区名称
    private String depName;
    //是否可用
    private boolean enabled;
    //用户是否过期
    private boolean accountNonExpired;
    //用户是否锁定
    private boolean accountNonLocked;
    //用户证书是否有效
    private boolean credentialsNonExpired;

    //无参构造器
    public SysUsersEntity(){}

    //有参构造器
    public SysUsersEntity(Integer sysUsersId,String username,String name,String password,Date dtCreate,Date lastLogin,Date deadline,String loginIp,String vQzjgid,String vQzjgmc,String depId,String depName,boolean enabled,boolean accountNonExpired,boolean accountNonLocked,boolean credentialsNonExpired){
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
    @Column(name = "sys_users_id" )
    public Integer getSysUsersId() {
        return sysUsersId;
    }

    public void setSysUsersId(int sysUsersId) {
        this.sysUsersId = sysUsersId;
    }

    @Basic
    @Column(name = "username" )
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "name" , nullable = false )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password" )
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "dt_create" , nullable = false )
    public Date getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Date dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Basic
    @Column(name = "last_login" , nullable = false )
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "deadline" , nullable = false )
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "login_ip" , nullable = false )
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Basic
    @Column(name = "v_qzjgid" , nullable = false )
    public String getVQzjgid() {
        return vQzjgid;
    }

    public void setVQzjgid(String vQzjgid) {
        this.vQzjgid = vQzjgid;
    }

    @Basic
    @Column(name = "v_qzjgmc" , nullable = false )
    public String getVQzjgmc() {
        return vQzjgmc;
    }

    public void setVQzjgmc(String vQzjgmc) {
        this.vQzjgmc = vQzjgmc;
    }

    @Basic
    @Column(name = "dep_id" , nullable = false )
    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Basic
    @Column(name = "dep_name" , nullable = false )
    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Basic
    @Column(name = "enabled" , nullable = false )
    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "account_non_expired" , nullable = false )
    public boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Basic
    @Column(name = "account_non_locked" , nullable = false )
    public boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Basic
    @Column(name = "credentials_non_expired" , nullable = false )
    public boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUsersEntity that = (SysUsersEntity) o;

        return 
            Objects.equals(sysUsersId, that.sysUsersId)  && 
            (username != null ? username.equals(that.username) : that.username == null)  && 
            (name != null ? name.equals(that.name) : that.name == null)  && 
            (password != null ? password.equals(that.password) : that.password == null)  && 
            (getDtCreate() != null ? getDtCreate().equals(that.getDtCreate()) : that.getDtCreate() == null)  && 
            (getLastLogin() != null ? getLastLogin().equals(that.getLastLogin()) : that.getLastLogin() == null)  && 
            (getDeadline() != null ? getDeadline().equals(that.getDeadline()) : that.getDeadline() == null)  && 
            (loginIp != null ? loginIp.equals(that.loginIp) : that.loginIp == null)  && 
            (vQzjgid != null ? vQzjgid.equals(that.vQzjgid) : that.vQzjgid == null)  && 
            (vQzjgmc != null ? vQzjgmc.equals(that.vQzjgmc) : that.vQzjgmc == null)  && 
            (depId != null ? depId.equals(that.depId) : that.depId == null)  && 
            (depName != null ? depName.equals(that.depName) : that.depName == null)  && 
            enabled == that.enabled  && 
            accountNonExpired == that.accountNonExpired  && 
            accountNonLocked == that.accountNonLocked  && 
            credentialsNonExpired == that.credentialsNonExpired ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (getSysUsersId() != null ? getSysUsersId().hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (getDtCreate() != null ? getDtCreate().hashCode() : 0);
        result = 31 * result + (getLastLogin() != null ? getLastLogin().hashCode() : 0);
        result = 31 * result + (getDeadline() != null ? getDeadline().hashCode() : 0);
        result = 31 * result + (loginIp != null ? loginIp.hashCode() : 0);
        result = 31 * result + (vQzjgid != null ? vQzjgid.hashCode() : 0);
        result = 31 * result + (vQzjgmc != null ? vQzjgmc.hashCode() : 0);
        result = 31 * result + (depId != null ? depId.hashCode() : 0);
        result = 31 * result + (depName != null ? depName.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (accountNonExpired ? 1 : 0);
        result = 31 * result + (accountNonLocked ? 1 : 0);
        result = 31 * result + (credentialsNonExpired ? 1 : 0);
        return result;
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
}
