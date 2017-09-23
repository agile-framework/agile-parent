package com.agileframework.agileclient.mvc.model.dao;

import com.agileframework.agileclient.mvc.model.entity.LogTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
* Created by 佟盟
*/
public interface LogTableRepository extends JpaRepository<LogTableEntity,Integer> {

}
