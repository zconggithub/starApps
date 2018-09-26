package com.starapp.shiro;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @date:2018/9/2417:51
 * @author:Allen
 * @description:shiro整合，realm
 */
public class UserRealm extends AuthorizingRealm {

    private Logger log=Logger.getLogger(this.getClass());

    /**
     * 方法实现用户的授权逻辑，
     * 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("----------doGetAuthorizationInfo方法被调用----------");
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        return null;
    }

    /**
     * 方法实现用户登录时的认证逻辑，进行身份验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return null;
    }
}
