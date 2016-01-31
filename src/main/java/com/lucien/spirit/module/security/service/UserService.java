package com.lucien.spirit.module.security.service;

import java.util.Date;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucien.spirit.core.shiro.authc.PasswordHelper;
import com.lucien.spirit.module.security.dao.UserDao;
import com.lucien.spirit.module.security.model.User;

@Service
public class UserService {
    
    @Autowired
    UserDao userDao;

    @Transactional(readOnly = true)
    public Page<User> findAllForPagination(int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        Page<User> users = userDao.findAll(pageable);
        return users;
    }

	public void save(User user) {
		userDao.save(user);
	}
	
	public void update(User user) {
		User userTemp = userDao.findOne(user.getId());
		userTemp.setEmployeeId(user.getEmployeeId());
		userTemp.setMobile(user.getMobile());
		userTemp.setName(user.getName());
		userTemp.setRealName(user.getRealName());
		userTemp.setPassword(user.getPassword());
		userTemp = PasswordHelper.generatePassword(userTemp);
		userDao.save(userTemp);
	}
	
	public User findOne(long id) {
		return userDao.findOne(id);
	}
	
	public User findUserByName(String name) {
		return userDao.findUserByName(name);
	}
	
	public void delete(long id) {
		userDao.delete(id);
	}

	/**
	 * 查询单个用户，并加载角色列表
	 * @param id
	 * @return
	 */
	@Transactional
    public User findOneAndRole(Long id) {
        User user = userDao.findOne(id);
        Hibernate.initialize(user); // 强制加载用户角色列表
        return user;
    }

	/**
	 * 根据客户id更新最后登录时间
	 * @param id
	 */
    public void updateLastLogin(Long id) {
        userDao.updateLastLogin(id, new Date());
    }
}
