package com.lucien.spirit.module.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucien.spirit.module.system.dao.LogDao;
import com.lucien.spirit.module.system.model.Log;

@Service
public class LogService {

	@Autowired
	private LogDao logDao;
	
	public List<Log> findAll() {
		return logDao.findAll();
	}

    public Page<Log> findAllForPagination(int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        return logDao.findAll(pageable);
    }

}
