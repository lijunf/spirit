package com.lucien.spirit.module.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucien.spirit.module.system.dao.DictEntryDao;
import com.lucien.spirit.module.system.model.DictEntry;

@Service
public class DictEntryService {

	@Autowired
	private DictEntryDao dictEntryDao;
	
	public void save(DictEntry dictEntry) {
		dictEntryDao.save(dictEntry);
	}

	public List<DictEntry> findByDictTypeId(String dictTypeId) {
		return dictEntryDao.findByDictTypeId(dictTypeId);
	}

    public DictEntry findOne(String dictTypeId, String dictId) {
        return dictEntryDao.findOne(dictTypeId, dictId);
    }
}
