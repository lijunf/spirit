package com.lucien.spirit.module.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucien.spirit.module.person.model.Person;
import com.lucien.spirit.module.person.repository.PersonRepository;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    @Transactional(readOnly = true)
    public Page<Person> findAllForPagination(int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        Page<Person> persons = personRepository.findAll(pageable);
        return persons;
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public Person findOne(String id) {
        return personRepository.findOne(id);
    }

    public void delete(String id) {
        personRepository.delete(id);
    }
}
