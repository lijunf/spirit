package com.lucien.spirit.module.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.lucien.spirit.module.person.model.Person;

@Repository
public interface PersonDao extends JpaRepository<Person, String>, JpaSpecificationExecutor<Person> {

}
