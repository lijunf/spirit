package com.lucien.spirit.core.shiro.spring;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.lucien.spirit.module.security.model.Resource;
import com.lucien.spirit.module.security.repository.ResourceRepository;

/**
 * @Title: ChainDefinitionSectionMetaSource.java 
 * @Package com.lucien.spirit.core.shiro.spring 
 * @Description: 动态创建filterchaindefinitions 
 * @author lucien   
 * @date 2016年1月28日 下午10:43:04 
 * @version V1.0
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

    @Autowired
    ResourceRepository resourceRepository;
    
    private String filterChainDefinitions;
    
    /** 
     * 默认premission字符串 
     */  
    public static final String PREMISSION_STRING="perms[\"{0}\"]";  

    /**
     * 权限map
     */
    @Override
    public Section getObject() throws Exception {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("/login", "anon");
        map.put("/Kaptcha.jpg", "anon");
        map.put("/resources/**", "anon");
        map.put("/home", "authc");
        
        Ini ini = new Ini();  
        //加载默认的url  
        ini.load(filterChainDefinitions);  
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);

        List<Resource> resources = resourceRepository.findAll();
        if (resources == null || resources.size() == 0) {
            initResource();
            resources = resourceRepository.findAll();
        }
        for (Resource resource : resources) {
        	String url = resource.getHref();
        	if (url.contains(";")) {
        		// 如果资源权限中保护分号，则代表多个权限
        		String[] urls = url.split(";");
        		for (String _url : urls) {
        			section.put(_url, MessageFormat.format(PREMISSION_STRING, resource.getPermission()));
        		}
        	} else {
        		section.put(url, MessageFormat.format(PREMISSION_STRING, resource.getPermission()));
        	}
        }
        return section;
    }
    
    /**
     * 初始化资源
     */
    private void initResource() {
        Resource resource = null;
        Resource sysRes = new Resource("system:manage", "系统设置", "/", null, Resource.TYPE_MENU, 1, "glyphicon-cog");
        resourceRepository.saveAndFlush(sysRes);
        
        Resource userResource = new Resource("user:query", "用户管理", "/security/user/list", sysRes.getId(), Resource.TYPE_MENU, 1, "glyphicon-user");
        resourceRepository.saveAndFlush(userResource);
        resource = new Resource("user:add", "添加用户", "/security/user/create", userResource.getId(), Resource.TYPE_BTN, 1);
        resourceRepository.save(resource);
        resource = new Resource("user:edit", "编辑用户", "/security/user/edit/**;/security/user/grant/**", userResource.getId(), Resource.TYPE_BTN, 2);
        resourceRepository.save(resource);
        resource = new Resource("user:delete", "删除用户", "/security/user/delete/**", userResource.getId(), Resource.TYPE_BTN, 3);
        resourceRepository.save(resource);
        
        Resource roleResource = new Resource("role:query", "角色管理", "/security/role/list", sysRes.getId(), Resource.TYPE_MENU, 2, "glyphicon-asterisk");
        resourceRepository.saveAndFlush(roleResource);
        resource = new Resource("role:add", "添加角色", "/security/role/create", roleResource.getId(), Resource.TYPE_BTN, 1);
        resourceRepository.save(resource);
        resource = new Resource("role:edit", "编辑角色", "/security/role/edit/**", roleResource.getId(), Resource.TYPE_BTN, 2);
        resourceRepository.save(resource);
        resource = new Resource("role:delete", "删除角色", "/security/role/delete/**", roleResource.getId(), Resource.TYPE_BTN, 3);
        resourceRepository.save(resource);
        
        Resource resourceResource = new Resource("resource:query", "资源管理", "/security/resource/list", sysRes.getId(), Resource.TYPE_MENU, 3, "glyphicon-th-list");
        resourceRepository.saveAndFlush(resourceResource);
        resource = new Resource("resource:add", "添加资源", "/security/resource/create", resourceResource.getId(), Resource.TYPE_BTN, 1);
        resourceRepository.save(resource);
        resource = new Resource("resource:edit", "编辑资源", "/security/resource/edit/**", resourceResource.getId(), Resource.TYPE_BTN, 2);
        resourceRepository.save(resource);
        resource = new Resource("resource:delete", "删除资源", "/security/resource/delete/**", resourceResource.getId(), Resource.TYPE_BTN, 3);
        resourceRepository.save(resource);
        
        Resource cusRes = new Resource("person:manage", "客户关系", "/", null, Resource.TYPE_MENU, 2, "glyphicon-star");
        resourceRepository.saveAndFlush(cusRes);
        Resource personResource = new Resource("person:query", "客户管理", "/person/list", cusRes.getId(), Resource.TYPE_MENU, 1, "glyphicon-user");
        resourceRepository.saveAndFlush(personResource);
        resource = new Resource("person:add", "添加客户", "/person/create", personResource.getId(), Resource.TYPE_BTN, 1);
        resourceRepository.save(resource);
        resource = new Resource("person:edit", "编辑客户", "/person/edit/**", personResource.getId(), Resource.TYPE_BTN, 2);
        resourceRepository.save(resource);
        resource = new Resource("person:delete", "删除客户", "/person/delete/**", personResource.getId(), Resource.TYPE_BTN, 3);
        resourceRepository.save(resource);
        
        Resource testRes = new Resource("test:manage", "测试菜单", "/", null, Resource.TYPE_MENU, 3, "glyphicon-remove");
        resourceRepository.saveAndFlush(testRes);
        Resource testResource = new Resource("test:query", "测试权限", "/test/list", testRes.getId(), Resource.TYPE_MENU, 1, "glyphicon-trash");
        resourceRepository.saveAndFlush(testResource);
        
        Resource test2Res = new Resource("test2:manage", "测试菜单", "/", null, Resource.TYPE_MENU, 4, "glyphicon-remove");
        resourceRepository.saveAndFlush(test2Res);
        Resource test2Resource = new Resource("test2:query", "测试权限", "/test2/list", test2Res.getId(), Resource.TYPE_MENU, 1, "glyphicon-trash");
        resourceRepository.saveAndFlush(test2Resource);
        
        Resource test3Res = new Resource("test3:manage", "测试菜单", "/", null, Resource.TYPE_MENU, 5, "glyphicon-remove");
        resourceRepository.saveAndFlush(test3Res);
        Resource test3Resource = new Resource("test3:query", "测试权限", "/test3/list", test3Res.getId(), Resource.TYPE_MENU, 1, "glyphicon-trash");
        resourceRepository.saveAndFlush(test3Resource);
        
        Resource test4Res = new Resource("test4:manage", "测试菜单", "/", null, Resource.TYPE_MENU, 5, "glyphicon-remove");
        resourceRepository.saveAndFlush(test4Res);
        Resource test4Resource = new Resource("test4:query", "测试权限", "/test4/list", test4Res.getId(), Resource.TYPE_MENU, 1, "glyphicon-trash");
        resourceRepository.saveAndFlush(test4Resource);
    }
    
    /** 
     * 通过filterChainDefinitions对默认的url过滤定义 
     * @param filterChainDefinitions 默认的url过滤定义 
     */  
    public void setFilterChainDefinitions(String filterChainDefinitions) {  
        this.filterChainDefinitions = filterChainDefinitions;  
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
