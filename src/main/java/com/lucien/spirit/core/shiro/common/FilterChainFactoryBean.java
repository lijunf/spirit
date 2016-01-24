package com.lucien.spirit.core.shiro.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.lucien.spirit.module.security.model.Resource;
import com.lucien.spirit.module.security.repository.ResourceRepository;

public class FilterChainFactoryBean implements FactoryBean<Map<String, String>> {

	@Autowired
	ResourceRepository resourceRepository;

	/**
	 * 权限map
	 */
	@Override
	public Map<String, String> getObject() throws Exception {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("/login", "anon");
		map.put("/Kaptcha.jpg", "anon");
		map.put("/resources/**", "anon");

		List<Resource> rs = resourceRepository.findAll();
		if (rs == null || rs.size() == 0) {
		    initResource();
		    rs = resourceRepository.findAll();
		}
		for (Resource r : rs) {
			map.put(r.getHref(), "authc,perms[" + r.getId() + "]");
		}
		map.put("/home", "authc");
		return map;
	}
	
	/**
     * 初始化资源
     */
    private void initResource() {
        Resource resource = null;
        Resource sysRes = new Resource("系统管理", "/", null, Resource.TYPE_MENU, 1);
        resourceRepository.saveAndFlush(sysRes);
        resource = new Resource("用户管理", "/security/user/list", sysRes.getId(), Resource.TYPE_MENU, 1);
        resourceRepository.save(resource);
        resource = new Resource("角色管理", "/security/role/list", sysRes.getId(), Resource.TYPE_MENU, 2);
        resourceRepository.save(resource);
        resource = new Resource("资源管理", "/security/resource/list", sysRes.getId(), Resource.TYPE_MENU, 3);
        resourceRepository.save(resource);
    }

	@Override
	public Class<?> getObjectType() {
		return Map.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
