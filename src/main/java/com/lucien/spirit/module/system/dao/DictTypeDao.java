package com.lucien.spirit.module.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucien.spirit.module.system.model.DictType;

@Repository
public interface DictTypeDao extends JpaRepository<DictType, String> {

}
