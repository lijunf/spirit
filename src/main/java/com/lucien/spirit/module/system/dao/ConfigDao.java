package com.lucien.spirit.module.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucien.spirit.module.system.model.Config;

@Repository
public interface ConfigDao extends JpaRepository<Config, Long> {

}
