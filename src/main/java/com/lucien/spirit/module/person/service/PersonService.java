package com.lucien.spirit.module.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucien.spirit.core.jpa.Criteria;
import com.lucien.spirit.core.jpa.Restrictions;
import com.lucien.spirit.module.person.dao.PersonDao;
import com.lucien.spirit.module.person.model.Person;

@Service
public class PersonService {
    @Autowired
    PersonDao personDao;

    @Transactional(readOnly = true)
    public Page<Person> findAllForPagination(int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        Page<Person> persons = personDao.findAll(pageable);
        return persons;
    }
    
    @Transactional(readOnly = true)
    public Page<Person> findAllForPagination(int page, int size, Person person) {
        Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        Criteria<Person> criteria = new Criteria<Person>();  
        criteria.add(Restrictions.like("name", person.getName(), true)); 
        criteria.add(Restrictions.eq("age", person.getAge(), true)); 
        Page<Person> persons = personDao.findAll(criteria, pageable);
        return persons;
    }

    public void save(Person person) {
        personDao.save(person);
    }
    
    public void save(List<Person> list) {
        personDao.save(list);
    }

    public Person findOne(String id) {
        return personDao.findOne(id);
    }

    public void delete(String id) {
        personDao.delete(id);
    }

}
