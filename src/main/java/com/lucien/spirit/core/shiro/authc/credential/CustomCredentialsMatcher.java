package com.lucien.spirit.core.shiro.authc.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import com.lucien.spirit.core.shiro.ShiroUser;
import com.lucien.spirit.module.security.dao.UserDao;
import com.lucien.spirit.module.security.model.User;

/**
 * 验证密码服务,可以提供密码错误登录次数的限制
 * 
 * @Filename : CustomCredentialsMatcher.java
 * @Package : com.lucien.spirit.security.shiro.authc.credential
 * @Description : TODO
 * @author : lijunf
 * @CreateDate : 2016年1月21日
 */
public class CustomCredentialsMatcher extends HashedCredentialsMatcher {
	
	@Autowired
    UserDao userDao;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        boolean result = super.doCredentialsMatch(token, info);
        ShiroUser principal = (ShiroUser) info.getPrincipals().getPrimaryPrincipal();
        if (!result) {
        	User user = userDao.findOne(principal.getId());
            if (user.getErrorNum() > 2) {	
            	throw new LockedAccountException();	// 密码输错3次
            } else {
            	user.setErrorNum(user.getErrorNum() + 1);
            	userDao.save(user);
            }
        } else {
        	if (principal.getErrorNo() != 0) {
        		User user = userDao.findOne(principal.getId());
        		user.setErrorNum(0);
        		userDao.save(user);
        		principal.setErrorNo(0);
        	}
        }
        return result;
    }

}
