package com.lucien.spirit.module.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucien.spirit.module.system.dao.ConfigDao;
import com.lucien.spirit.module.system.model.Config;

public class ConfigService {

	@Autowired
	private ConfigDao configDao;
	
	public List<Config> findAll() {
		return configDao.findAll();
	}
	
}
