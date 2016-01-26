package com.lucien.spirit.module.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucien.spirit.module.security.model.Resource;
import com.lucien.spirit.module.security.repository.ResourceRepository;

@Service
public class ResourceService {
    
    @Autowired
    ResourceRepository resourceRepository;
    
    public List<Resource> findAll() {
        return resourceRepository.findAll();
    }

    public void delete(Long id) {
        resourceRepository.delete(id);
    }

}
