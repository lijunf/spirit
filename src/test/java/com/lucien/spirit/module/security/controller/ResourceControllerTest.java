package com.lucien.spirit.module.security.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.lucien.spirit.module.security.model.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { 
		"classpath:applicationContext.xml", 
		"classpath:applicationContext-shiro.xml",
		"classpath:dispatcher-servlet.xml"
		})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class ResourceControllerTest {
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
    @Rollback(false)
	public void testList() {
		try {
			mockMvc.perform((get("/security/resource/list")))
			.andExpect(status().isOk())
			.andDo(print());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testCreate() {
		try {
			mockMvc.perform((get("/security/resource/create")))
			.andExpect(status().isOk())
			.andDo(print());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testSave() {
		try {
			mockMvc.perform((post("/security/resource/create")
					.param("permission", "test:manage")
					.param("name", "测试菜单")
					.param("href", "/")
					.param("resType", "" + Resource.TYPE_MENU)
					.param("orderNo", "" + 1)
					.param("iconCls", "glyphicon-remove")))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(model().attributeHasNoErrors("resource"));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testEdit() {
		try {
			mockMvc.perform((get("/security/resource/edit/1")))
			.andExpect(status().isOk())
			.andDo(print());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testUpdate() {
		try {
			mockMvc.perform((post("/security/resource/edit/1")
					.param("id", "" + 1)
					.param("permission", "test:manage")
					.param("name", "测试菜单")
					.param("href", "/")
					.param("resType", "" + Resource.TYPE_MENU)
					.param("orderNo", "" + 1)
					.param("iconCls", "glyphicon-remove")))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(model().attributeHasNoErrors("resource"));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testDelete() {
		try {
			mockMvc.perform((get("/security/resource/delete/1")))
			.andExpect(status().isOk())
			.andDo(print());
		} catch (Exception e) {
			fail();
		}
	}

}
