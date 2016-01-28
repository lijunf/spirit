package com.lucien.spirit.core.shiro.realm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lucien.spirit.module.security.controller.LoginController;
import com.lucien.spirit.module.security.model.Resource;
import com.lucien.spirit.module.security.model.Role;
import com.lucien.spirit.module.security.model.User;
import com.lucien.spirit.module.security.repository.UserRepository;

public class JpaRealm extends AuthorizingRealm implements Serializable {

    private static final long serialVersionUID = 2053039661926394526L;

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserRepository userRepository;

    /*
     * 授权信息处理
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userid = principals.getPrimaryPrincipal().toString();
        User user = this.userRepository.findOne(Long.parseLong(userid));

        if (null != user) {
        	Set<String>  permissions = new HashSet<String>();
            SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo();
            for (Role role : user.getRoles()) {
				authorization.addRole(role.getName());
				for (Resource resource : role.getResource()) {
					setMenuPerms(permissions, resource.getParent());
					permissions.add(resource.getResCode());
				}
            }
            authorization.addStringPermissions(permissions);
            return authorization;
        }

        return null;
    }
    
	private void setMenuPerms(Set<String> permissions, Resource resource) {
		if (resource != null) {
			permissions.add(resource.getResCode());
			if (resource.getParent() != null) {
				setMenuPerms(permissions, resource.getParent());
			}
		}
	}

    /*
     * 认证信息处理
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();
        User user = this.userRepository.findUserByName(username);

        if (null == user) {
            log.error("没有相关用户!");
            throw new UnknownAccountException();
        }

        String principal = String.valueOf(user.getId());
        String hashedCredentials = user.getPasswordHash();
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getName() + new String(user.getPasswordSalt()));
        String realmName = getName();

        SimpleAuthenticationInfo authentication = new SimpleAuthenticationInfo(principal, hashedCredentials,
                credentialsSalt, realmName);
        return authentication;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
