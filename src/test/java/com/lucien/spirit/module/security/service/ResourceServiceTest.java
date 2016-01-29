package com.lucien.spirit.module.security.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.lucien.spirit.module.security.model.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class ResourceServiceTest {

	@Autowired
	ResourceService resourceService;
	
	@Test
	@Rollback(false)
	public void testFindAll() {
		resourceService.findAll();
	}

	@Test
	@Rollback(false)
	public void testFindTopList() {
		resourceService.findTopList();
	}
	
	@Test
	public void testSave() {
		Resource resource = new Resource("test:manage", "测试菜单", "/", null, Resource.TYPE_MENU, 1, "glyphicon-remove");
        resourceService.save(resource);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOne() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}
}
