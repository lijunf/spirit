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
import com.lucien.spirit.module.security.model.User;
import com.lucien.spirit.module.security.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<User> findAllForPagination(int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

	public void save(User user) {
		userRepository.save(user);
	}
	
	public void update(User user) {
		User userTemp = userRepository.findOne(user.getId());
		userTemp.setEmployeeId(user.getEmployeeId());
		userTemp.setMobile(user.getMobile());
		userTemp.setName(user.getName());
		userTemp.setRealName(user.getRealName());
		userTemp.setPassword(user.getPassword());
		userTemp = PasswordHelper.generatePassword(userTemp);
		userRepository.save(userTemp);
	}
	
	public User findOne(long id) {
		return userRepository.findOne(id);
	}
	
	public User findUserByName(String name) {
		return userRepository.findUserByName(name);
	}
	
	public void delete(long id) {
		userRepository.delete(id);
	}

	/**
	 * 查询单个用户，并加载角色列表
	 * @param id
	 * @return
	 */
	@Transactional
    public User findOneAndRole(Long id) {
        User user = userRepository.findOne(id);
        Hibernate.initialize(user); // 强制加载用户角色列表
        return user;
    }

	/**
	 * 根据客户id更新最后登录时间
	 * @param id
	 */
    public void updateLastLogin(Long id) {
        userRepository.updateLastLogin(id, new Date());
    }
}
