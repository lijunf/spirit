package com.lucien.spirit.module.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.lucien.spirit.module.system.model.Log;

@Repository
public interface LogDao extends JpaRepository<Log, Long>, JpaSpecificationExecutor<Log> {

}
