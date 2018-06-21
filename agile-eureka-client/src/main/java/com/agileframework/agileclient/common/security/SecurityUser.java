package com.agileframework.agileclient.common.security;

import com.agileframework.agileclient.mvc.model.entity.SysAuthoritiesEntity;
import com.agileframework.agileclient.mvc.model.entity.SysUsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by 佟盟 on 2017/9/26
 */
public class SecurityUser extends SysUsersEntity implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Set<SysAuthoritiesEntity> sysAuthoritiesEntities;

    SecurityUser(SysUsersEntity user, Set<SysAuthoritiesEntity> sysAuthoritiesEntities) {
        if(user != null)
        {
            this.setSysUsersId(user.getSysUsersId());
            this.setName(user.getName());
            this.setSysUsersId(user.getSysUsersId());
            this.setUsername(user.getUsername());
            this.setName(user.getName());
            this.setPassword(user.getPassword());
            this.setDtCreate(user.getDtCreate());
            this.setLastLogin(user.getLastLogin());
            this.setDeadline(user.getDeadline());
            this.setLoginIp(user.getLoginIp());
            this.setVQzjgid(user.getVQzjgid());
            this.setVQzjgmc(user.getVQzjgmc());
            this.setDepId(user.getDepId());
            this.setDepName(user.getDepName());
            this.setEnabled(user.getEnabled());
            this.setAccountNonExpired(user.getAccountNonExpired());
            this.setAccountNonLocked(user.getAccountNonLocked());
            this.setCredentialsNonExpired(user.getCredentialsNonExpired());
            this.sysAuthoritiesEntities = sysAuthoritiesEntities;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if(sysAuthoritiesEntities != null)
        {
            for (SysAuthoritiesEntity role : sysAuthoritiesEntities) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getAuthorityName());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return getEnabled();
    }
}
