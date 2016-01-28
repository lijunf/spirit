package com.lucien.spirit.module.security.service;

import java.util.List;

import javax.servlet.ServletContext;

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

    @Transactional
    public void delete(Long id) {
        resourceRepository.deleteRoleById(id);
        resourceRepository.delete(id);
    }

	public void save(Resource resource) {
		resourceRepository.save(resource);
	}

	public Resource findOne(Long id) {
		return resourceRepository.findOne(id);
	}
	
	/**
	 * 刷新资源树全局缓存
	 * @param context
	 */
	public void refreshCache(ServletContext context) {
		List<Resource> topResourceList = findTopList();
		context.setAttribute("topResourceList", topResourceList);
	}

}
