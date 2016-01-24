package com.lucien.spirit.module.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucien.spirit.module.person.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    
}
