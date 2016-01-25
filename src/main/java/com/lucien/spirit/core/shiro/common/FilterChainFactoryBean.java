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
			map.put(r.getHref(), "authc,perms[" + r.getResCode() + "]");
		}
		map.put("/home", "authc");
		return map;
	}
	
	/**
     * 初始化资源
     */
    private void initResource() {
        Resource resource = null;
        Resource sysRes = new Resource("system:manage", "系统管理", "/", null, Resource.TYPE_MENU, 1);
        resourceRepository.saveAndFlush(sysRes);
        
        Resource userResource = new Resource("user:query", "用户管理", "/security/user/list", sysRes.getId(), Resource.TYPE_MENU, 1);
        resourceRepository.saveAndFlush(userResource);
        resource = new Resource("user:add", "添加用户", "/security/user/create", userResource.getId(), Resource.TYPE_BTN, 1);
        resourceRepository.save(resource);
        resource = new Resource("user:edit", "编辑用户", "/security/user/edit", userResource.getId(), Resource.TYPE_BTN, 2);
        resourceRepository.save(resource);
        resource = new Resource("user:delete", "删除用户", "/security/user/delete/**", userResource.getId(), Resource.TYPE_BTN, 3);
        resourceRepository.save(resource);
        
        Resource roleResource = new Resource("role:query", "角色管理", "/security/role/list", sysRes.getId(), Resource.TYPE_MENU, 2);
        resourceRepository.saveAndFlush(roleResource);
        resource = new Resource("role:add", "添加角色", "/security/role/form", roleResource.getId(), Resource.TYPE_BTN, 1);
        resourceRepository.save(resource);
        resource = new Resource("role:edit", "编辑角色", "/security/role/edit", roleResource.getId(), Resource.TYPE_BTN, 2);
        resourceRepository.save(resource);
        resource = new Resource("role:delete", "删除角色", "/security/role/delete/**", roleResource.getId(), Resource.TYPE_BTN, 3);
        resourceRepository.save(resource);
        
        Resource resourceResource = new Resource("resource:query", "资源管理", "/security/resource/list", sysRes.getId(), Resource.TYPE_MENU, 3);
        resourceRepository.saveAndFlush(resourceResource);
        resource = new Resource("resource:add", "添加资源", "/security/resource/create", resourceResource.getId(), Resource.TYPE_BTN, 1);
        resourceRepository.save(resource);
        resource = new Resource("resource:edit", "编辑资源", "/security/resource/edit", resourceResource.getId(), Resource.TYPE_BTN, 2);
        resourceRepository.save(resource);
        resource = new Resource("resource:delete", "删除资源", "/security/resource/delete/**", resourceResource.getId(), Resource.TYPE_BTN, 3);
        resourceRepository.save(resource);
        
        Resource cusRes = new Resource("customer:manage", "客户关系", "/", null, Resource.TYPE_MENU, 2);
        resourceRepository.saveAndFlush(cusRes);
        Resource personResource = new Resource("person:query", "客户管理", "/person/list", cusRes.getId(), Resource.TYPE_MENU, 1);
        resourceRepository.saveAndFlush(personResource);
        resource = new Resource("person:add", "添加客户", "/person/form", personResource.getId(), Resource.TYPE_BTN, 1);
        resourceRepository.save(resource);
        resource = new Resource("person:edit", "编辑客户", "/person/edit", personResource.getId(), Resource.TYPE_BTN, 2);
        resourceRepository.save(resource);
        resource = new Resource("person:delete", "删除客户", "/person/delete/**", personResource.getId(), Resource.TYPE_BTN, 3);
        resourceRepository.save(resource);
        
        Resource testRes = new Resource("test:manage", "测试菜单", "/", null, Resource.TYPE_MENU, 3);
        resourceRepository.saveAndFlush(testRes);
        Resource testResource = new Resource("test:query", "测试权限", "/test/list", testRes.getId(), Resource.TYPE_MENU, 1);
        resourceRepository.saveAndFlush(testResource);
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
