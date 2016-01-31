package com.lucien.spirit.module.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucien.spirit.module.system.dao.SysParamDao;
import com.lucien.spirit.module.system.model.SysParam;

public class SysParamService {

	@Autowired
	private SysParamDao sysParamDao;
	
	public List<SysParam> findAll() {
		return sysParamDao.findAll();
	}
	
}
