package com.lucien.spirit.module.security.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucien.spirit.module.security.model.Resource;
import com.lucien.spirit.module.security.repository.ResourceRepository;

@Service
public class ResourceService {
    
    @Autowired
    ResourceRepository resourceRepository;
    
    public List<Resource> findAll() {
        return resourceRepository.findAll();
    }
    
    @Transactional
    public List<Resource> findTopList() {
        List<Resource> topResourceList = resourceRepository.findTopList();
        for (Resource resource : topResourceList) {
            List<Resource> subReses = resource.getSubResource();
            for (Resource subRes : subReses) {
                List<Resource> subReses2 = subRes.getSubResource();
                for (Resource subRes2 : subReses2) {
                    Hibernate.initialize(subRes2);     // 强制刷新整棵资源数，只支持三层结构
                }
            }
        }
        return topResourceList;
    }

    public void delete(Long id) {
    	// TODO 删除资源与角色的绑定关系
        resourceRepository.delete(id);
    }

}
