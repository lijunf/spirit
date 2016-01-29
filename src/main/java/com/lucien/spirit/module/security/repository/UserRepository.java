package com.lucien.spirit.module.security.repository;


import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucien.spirit.module.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
	@Query("SELECT u FROM User u WHERE u.name = :name)")
	public User findUserByName(@Param("name") String name);
	
	/**
	 * 修改最后登录时间
	 * @param id
	 * @param date
	 */
	@Modifying
	@Transactional
    @Query(nativeQuery=true, value="UPDATE sys_users SET LAST_LOGIN = :date WHERE ID = :id")
	public void updateLastLogin(@Param("id") Long id, @Param("date") Date date);
}
