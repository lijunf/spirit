package com.lucien.spirit.module.security.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lucien.spirit.module.security.model.Resource;
import com.lucien.spirit.test.AbstractDaoTest;

public class ResourceDaoTest extends AbstractDaoTest {
	
	@Autowired
	ResourceDao resourceDao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSaveAndFlush() {
		Resource testRes = new Resource("test:manage", "测试菜单", "/", null, Resource.TYPE_MENU, 3, "glyphicon-remove");
        resourceDao.saveAndFlush(testRes);
        Resource testResource = new Resource("test:query", "测试权限", "/test/list", testRes.getId(), Resource.TYPE_MENU, 1, "glyphicon-trash");
        resourceDao.saveAndFlush(testResource);
        
        Resource test2Res = new Resource("test2:manage", "测试菜单", "/", null, Resource.TYPE_MENU, 4, "glyphicon-remove");
        resourceDao.saveAndFlush(test2Res);
        Resource test2Resource = new Resource("test2:query", "测试权限", "/test2/list", test2Res.getId(), Resource.TYPE_MENU, 1, "glyphicon-trash");
        resourceDao.saveAndFlush(test2Resource);
        
        Resource test3Res = new Resource("test3:manage", "测试菜单", "/", null, Resource.TYPE_MENU, 5, "glyphicon-remove");
        resourceDao.saveAndFlush(test3Res);
        Resource test3Resource = new Resource("test3:query", "测试权限", "/test3/list", test3Res.getId(), Resource.TYPE_MENU, 1, "glyphicon-trash");
        resourceDao.saveAndFlush(test3Resource);
        
        Resource test4Res = new Resource("test4:manage", "测试菜单", "/", null, Resource.TYPE_MENU, 5, "glyphicon-remove");
        resourceDao.saveAndFlush(test4Res);
        Resource test4Resource = new Resource("test4:query", "测试权限", "/test4/list", test4Res.getId(), Resource.TYPE_MENU, 1, "glyphicon-trash");
        resourceDao.saveAndFlush(test4Resource);
	}

}
