package com.lucien.spirit.module.system.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucien.spirit.module.system.dao.DictEntryDao;
import com.lucien.spirit.module.system.model.DictEntry;

public class DictEntryService {

	@Autowired
	private DictEntryDao dictEntryDao;
	
	public void save(DictEntry dictEntry) {
		dictEntryDao.save(dictEntry);
	}
}
