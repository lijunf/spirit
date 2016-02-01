package com.lucien.spirit.module.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucien.spirit.module.system.dao.ConfigDao;
import com.lucien.spirit.module.system.model.Config;

@Service
public class ConfigService {

	@Autowired
	private ConfigDao configDao;
	
	public List<Config> findAll() {
		return configDao.findAll();
	}

	public void save(Config config) {
		configDao.save(config);
	}

	public Config findOne(Long id) {
		return configDao.findOne(id);
	}

	public void delete(Long id) {
		configDao.delete(id);
	}

	public Config findByKey(String key) {
		return configDao.findByKey(key);
	}
}
