package com.lucien.spirit.module.security.service;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.lucien.spirit.module.security.model.Resource;
import com.lucien.spirit.test.AbstractServiceTest;

public class ResourceServiceTest extends AbstractServiceTest {

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
	@Ignore
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testFindOne() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testDelete() {
		fail("Not yet implemented");
	}
}
