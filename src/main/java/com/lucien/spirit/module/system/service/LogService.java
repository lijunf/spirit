package com.lucien.spirit.module.system.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucien.spirit.core.jpa.Criteria;
import com.lucien.spirit.core.jpa.Restrictions;
import com.lucien.spirit.module.system.dao.LogDao;
import com.lucien.spirit.module.system.model.Log;

@Service
public class LogService {

	@Autowired
	private LogDao logDao;
	
	public List<Log> findAll() {
		return logDao.findAll();
	}

    public Page<Log> findAllForPagination(int page, int size, Log log, Date beginDate, Date endDate) {
        Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        Criteria<Log> criteria = new Criteria<Log>();  
        criteria.add(Restrictions.eq("priority", log.getPriority(), true)); 
        criteria.add(Restrictions.eq("loginId", log.getLoginId(), true)); 
        criteria.add(Restrictions.like("msg", log.getMsg(), true)); 
        criteria.add(Restrictions.eq("ipAddr", log.getIpAddr(), true));
        criteria.add(Restrictions.gte("logDate", beginDate, true));
        criteria.add(Restrictions.lte("logDate", endDate, true));
        return logDao.findAll(criteria, pageable);
    }

}
