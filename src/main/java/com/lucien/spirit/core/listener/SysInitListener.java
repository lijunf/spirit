package com.lucien.spirit.core.listener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.shiro.crypto.hash.Sha512Hash;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lucien.spirit.module.person.model.Person;
import com.lucien.spirit.module.person.repository.PersonRepository;
import com.lucien.spirit.module.security.model.Resource;
import com.lucien.spirit.module.security.model.Role;
import com.lucien.spirit.module.security.model.User;
import com.lucien.spirit.module.security.repository.ResourceRepository;
import com.lucien.spirit.module.security.repository.RoleRepository;
import com.lucien.spirit.module.security.repository.UserRepository;

/**
 * 系统初始化数据,数据库不限.登录账户为 admin 密码admin
 * 
 * @Filename : SysInitListener.java
 * @Package : com.lucien.spirit.core.listener
 * @Description : Lucien基础服务平台
 * @author : lijunf
 * @CreateDate : 2016年1月21日
 */
public class SysInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        UserRepository userRepository = webApplicationContext.getBean(UserRepository.class);
        User user = userRepository.findUserByName("admin");
        if (user != null && user.getId() != null) {
        	return;
        }
        
        PersonRepository personRepository = webApplicationContext.getBean(PersonRepository.class);

        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String chars = "abcdefghijklmnopqrstuvwxyz";
            String name = String.valueOf(chars.charAt((int) (Math.random() * 26)));
            Person person = new Person();
            person.setName(name);
            person.setAge((i % 100) + 1);
            personList.add(person);
        }
        personRepository.save(personList);
        
        ResourceRepository resourceRepository = webApplicationContext.getBean(ResourceRepository.class);
        initResource(resourceRepository);
        List<Resource> resources = resourceRepository.findAll();

        RoleRepository roleRepository = webApplicationContext.getBean(RoleRepository.class);
        Role role = new Role();
        role.setName("admin");
        role.setDescription("系统管理员");
        role.setResource(resources);
        roleRepository.saveAndFlush(role);
        
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        
        byte[] passwordSalt = UUID.randomUUID().toString().getBytes();
        user = new User();
        user.setRoles(roles);
        user.setName("admin");
        user.setPasswordSalt(passwordSalt);
        user.setPassword("admin");
        user.setRealName("李俊锋");
        user.setMobile("13524595283");
        user.setEmail("lijunf@163.com");
        String passwordHash = new Sha512Hash(user.getPassword(), user.getName() + new String(passwordSalt), 99)
                .toString();
        user.setPasswordHash(passwordHash);
        userRepository.save(user);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private void initResource(ResourceRepository resourceRepository) {
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
}
