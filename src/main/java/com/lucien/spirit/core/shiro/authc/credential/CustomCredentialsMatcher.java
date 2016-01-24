package com.lucien.spirit.core.shiro.authc.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * 验证密码服务,可以提供密码错误登录次数的限制
 * 
 * @Filename : CustomCredentialsMatcher.java
 * @Package : com.lucien.spirit.security.shiro.authc.credential
 * @Description : Lucien基础服务平台
 * @author : lijunf
 * @CreateDate : 2016年1月21日
 */
public class CustomCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        boolean result = super.doCredentialsMatch(token, info);
        return result;
    }

}
