package com.lucien.spirit.module.security.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.lucien.spirit.module.security.model.Resource;
import com.lucien.spirit.test.AbstractControllerTest;

public class ResourceControllerTest extends AbstractControllerTest {
	
	@Test
    @Rollback(false)
	public void testList() throws Exception {
		mockMvc.perform((get("/security/resource/list")))
		.andExpect(status().isOk())
		.andDo(print());
	}

	@Test
	public void testCreate() throws Exception {
		mockMvc.perform((get("/security/resource/create")))
		.andExpect(status().isOk())
		.andDo(print());
	}

	@Test
	public void testSave() throws Exception {
		mockMvc.perform((post("/security/resource/create")
				.param("permission", "test:manage")
				.param("name", "测试菜单")
				.param("href", "/")
				.param("resType", "" + Resource.TYPE_MENU)
				.param("orderNo", "" + 1)
				.param("iconCls", "glyphicon-remove")))
		.andExpect(status().is3xxRedirection())
		.andDo(print())
		.andExpect(model().attributeHasNoErrors("resource"));
	}

	@Test
	public void testEdit() throws Exception {
		mockMvc.perform((get("/security/resource/edit/1")))
		.andExpect(status().isOk())
		.andDo(print());
	}

	@Test
	@Ignore
	public void testUpdate() throws Exception {
		mockMvc.perform((post("/security/resource/edit/1")
				.param("id", "" + 1)
				.param("permission", "test:manage")
				.param("name", "测试菜单")
				.param("href", "/")
				.param("resType", "" + Resource.TYPE_MENU)
				.param("orderNo", "" + 1)
				.param("iconCls", "glyphicon-remove")))
		.andExpect(status().is3xxRedirection())
		.andDo(print())
		.andExpect(model().attributeHasNoErrors("resource"));
	}

	@Test
	@Ignore
	public void testDelete() throws Exception {
		mockMvc.perform((get("/security/resource/delete/1")))
		.andExpect(status().is3xxRedirection())
		.andDo(print());
	}

}
