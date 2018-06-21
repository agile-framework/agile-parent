package com.agileframework.agileclient.mvc.model.entity;

import com.agileframework.agileclient.common.annotation.Remark;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by 佟盟
 */
@Entity
@Table(name = "sys_persistent_logins",  catalog = "agile_db")
@Remark("[系统管理]登陆用户信息")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysPersistentLoginsEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    @Remark("唯一标识")
    private Integer sysPersistentLoginsId;
    @Remark("用户名")
    private String username;
    @Remark("序列")
    private String series;
    @Remark("认证信息")
    private String token;
    @Remark("最后时间")
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
    @Column(name = "sys_persistent_logins_id" , nullable = false )
    public Integer getSysPersistentLoginsId() {
        return sysPersistentLoginsId;
    }

    public void setSysPersistentLoginsId(int sysPersistentLoginsId) {
        this.sysPersistentLoginsId = sysPersistentLoginsId;
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
    @Column(name = "series" , nullable = false )
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Basic
    @Column(name = "token" )
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "last_used" , nullable = false )
    public Timestamp getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Timestamp lastUsed) {
        this.lastUsed = lastUsed;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysPersistentLoginsEntity)) return false;
        SysPersistentLoginsEntity that = (SysPersistentLoginsEntity) object;
        return Objects.equals(getSysPersistentLoginsId(), that.getSysPersistentLoginsId()) &&
            Objects.equals(getUsername(), that.getUsername()) &&
            Objects.equals(getSeries(), that.getSeries()) &&
            Objects.equals(getToken(), that.getToken()) &&
            Objects.equals(getLastUsed(), that.getLastUsed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysPersistentLoginsId(), getUsername(), getSeries(), getToken(), getLastUsed());
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

    private SysPersistentLoginsEntity(Builder builder){
        this.sysPersistentLoginsId = builder.sysPersistentLoginsId;
        this.username = builder.username;
        this.series = builder.series;
        this.token = builder.token;
        this.lastUsed = builder.lastUsed;
    }

    public static class Builder{
        private Integer sysPersistentLoginsId;
        private String username;
        private String series;
        private String token;
        private Timestamp lastUsed;

        public Builder setSysPersistentLoginsId(int sysPersistentLoginsId) {
            this.sysPersistentLoginsId = sysPersistentLoginsId;
            return this;
        }
        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }
        public Builder setSeries(String series) {
            this.series = series;
            return this;
        }
        public Builder setToken(String token) {
            this.token = token;
            return this;
        }
        public Builder setLastUsed(Timestamp lastUsed) {
            this.lastUsed = lastUsed;
            return this;
        }
        public SysPersistentLoginsEntity build(){
            return new SysPersistentLoginsEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
