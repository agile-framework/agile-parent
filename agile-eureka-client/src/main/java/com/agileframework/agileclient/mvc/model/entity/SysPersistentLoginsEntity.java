package com.agileframework.agileclient.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_persistent_logins",  catalog = "agile_db")
public class SysPersistentLoginsEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private Integer sysPersistentLoginsId;
    //用户名
    private String username;
    //序列
    private String series;
    //认证信息
    private String token;
    //最后时间
    private Timestamp lastUsed;

    //无参构造器
    public SysPersistentLoginsEntity(){}

    //有参构造器
    public SysPersistentLoginsEntity(Integer sysPersistentLoginsId,String username,String series,String token,Timestamp lastUsed){
        this.sysPersistentLoginsId = sysPersistentLoginsId;
        this.username = username;
        this.series = series;
        this.token = token;
        this.lastUsed = lastUsed;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "sys_persistent_logins_id" )
    public Integer getSysPersistentLoginsId() {
        return sysPersistentLoginsId;
    }

    public void setSysPersistentLoginsId(int sysPersistentLoginsId) {
        this.sysPersistentLoginsId = sysPersistentLoginsId;
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
    @Column(name = "series" )
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Basic
    @Column(name = "token" , nullable = false )
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "last_used" )
    public Timestamp getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Timestamp lastUsed) {
        this.lastUsed = lastUsed;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysPersistentLoginsEntity that = (SysPersistentLoginsEntity) o;

        return 
            Objects.equals(sysPersistentLoginsId, that.sysPersistentLoginsId)  && 
            (username != null ? username.equals(that.username) : that.username == null)  && 
            (series != null ? series.equals(that.series) : that.series == null)  && 
            (token != null ? token.equals(that.token) : that.token == null)  && 
            (getLastUsed() != null ? getLastUsed().equals(that.getLastUsed()) : that.getLastUsed() == null) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (getSysPersistentLoginsId() != null ? getSysPersistentLoginsId().hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (series != null ? series.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (getLastUsed() != null ? getLastUsed().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SysPersistentLoginsEntity{" +
        "sysPersistentLoginsId=" + sysPersistentLoginsId +
        ",username='" + username + '\'' +
        ",series='" + series + '\'' +
        ",token='" + token + '\'' +
        ",lastUsed=" + lastUsed +
        '}';
    }
}
