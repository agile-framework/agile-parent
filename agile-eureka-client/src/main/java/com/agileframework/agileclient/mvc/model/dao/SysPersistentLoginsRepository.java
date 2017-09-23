package com.agileframework.agileclient.mvc.model.dao;

import com.agileframework.agileclient.mvc.model.entity.SysPersistentLoginsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
* Created by 佟盟
*/
public interface SysPersistentLoginsRepository extends JpaRepository<SysPersistentLoginsEntity,Integer> {

}
