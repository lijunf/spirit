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
		for (Resource r : rs) {
			map.put(r.getHref(), "authc,perms[" + r.getId() + "]");
		}
		map.put("/home", "authc");
		return map;
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
