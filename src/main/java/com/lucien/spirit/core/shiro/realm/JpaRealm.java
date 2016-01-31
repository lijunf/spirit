package com.lucien.spirit.core.shiro.realm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
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
import org.springframework.beans.factory.annotation.Autowired;

import com.lucien.spirit.core.constants.UserConstants;
import com.lucien.spirit.core.shiro.ShiroUser;
import com.lucien.spirit.module.security.dao.UserDao;
import com.lucien.spirit.module.security.model.Resource;
import com.lucien.spirit.module.security.model.Role;
import com.lucien.spirit.module.security.model.User;

public class JpaRealm extends AuthorizingRealm implements Serializable {

    private static final long serialVersionUID = 2053039661926394526L;

    @Autowired
    UserDao userDao;

    /*
     * 授权信息处理
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	ShiroUser principal = (ShiroUser) principals.getPrimaryPrincipal();
        User user = this.userDao.findOne(principal.getId());

        if (null != user) {
        	Set<String>  permissions = new HashSet<String>();
            SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo();
            for (Role role : user.getRoles()) {
				authorization.addRole(role.getName());
				for (Resource resource : role.getResource()) {
					setMenuPerms(permissions, resource.getParent());
					permissions.add(resource.getPermission());
				}
            }
            authorization.addStringPermissions(permissions);
            return authorization;
        }

        return null;
    }
    
	private void setMenuPerms(Set<String> permissions, Resource resource) {
		if (resource != null) {
			permissions.add(resource.getPermission());
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
        if (username == null) {  
            throw new AccountException("用户名不能为空");  
        }  
        
        User user = this.userDao.findUserByName(username);
        if (user == null) {
            throw new UnknownAccountException();
        }
        if (UserConstants.STATUS_ENABLE != user.getStatus()) {
        	throw new AccountException("用户名状态不正常");
        }

        // ShiroUser会保存用户Id，如果将来对用户实体进行缓存，会对使用findOne(Id)查找用户时有帮助，根据id查询才能使用缓存
        ShiroUser principal = new ShiroUser(user.getId(), user.getName(), user.getRealName(), user.getErrorNum());
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
    
    @Override
    public void onLogout(PrincipalCollection principals) {
        // 覆盖父类的方法，防止退出时用户权限缓存authorizationCache被清除
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
