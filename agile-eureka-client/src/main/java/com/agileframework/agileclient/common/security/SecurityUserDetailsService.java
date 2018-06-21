package com.agileframework.agileclient.common.security;

import com.agileframework.agileclient.mvc.model.dao.Dao;
import com.agileframework.agileclient.mvc.model.entity.SysAuthoritiesEntity;
import com.agileframework.agileclient.mvc.model.entity.SysUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 佟盟 on 2017/1/13
 * 从数据库中读入用户的密码，角色信息，是否锁定，账号是否过期等
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private Dao dao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUsersEntity user = dao.findOne("SELECT * FROM sys_users", SysUsersEntity.class);
        String sql = "SELECT\n" +
                "\tsys_authorities.SYS_AUTHORITY_ID,\n" +
                "\tsys_authorities.AUTHORITY_MARK,\n" +
                "\tsys_authorities.AUTHORITY_NAME,\n" +
                "\tsys_authorities.AUTHORITY_DESC,\n" +
                "\tsys_authorities.MESSAGE,\n" +
                "\tsys_authorities.`ENABLE`,\n" +
                "\tsys_authorities.ISSYS,\n" +
                "\tsys_authorities.MODULE_ID \n" +
                "FROM\n" +
                "\tsys_users\n" +
                "\tLEFT JOIN sys_bt_users_roles ON sys_users.SYS_USERS_ID = sys_bt_users_roles.USER_ID\n" +
                "\tLEFT JOIN sys_roles ON sys_roles.SYS_ROLES_ID = sys_bt_users_roles.ROLE_ID\n" +
                "\tLEFT JOIN sys_bt_roles_authorities ON sys_roles.SYS_ROLES_ID = sys_bt_roles_authorities.ROLE_ID\n" +
                "\tLEFT JOIN sys_authorities ON sys_authorities.SYS_AUTHORITY_ID = sys_bt_roles_authorities.AUTHORITY_ID \n" +
                "WHERE\n" +
                "\tsys_users.USERNAME = '%s'";
        sql = String.format(sql, username);
        @SuppressWarnings("unchecked")
        Set<SysAuthoritiesEntity> sysAuthoritiesEntities = new HashSet(dao.findAll(sql,SysAuthoritiesEntity.class));
        return new SecurityUser(user,sysAuthoritiesEntities);
    }
}
