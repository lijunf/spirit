package com.lucien.spirit.module.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucien.spirit.module.security.model.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
