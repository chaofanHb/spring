package com.cn.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by Administrator on 2017/6/12.
 */
public class Shiro extends AuthorizingRealm {

    private static String name = "zhangsan";
    private static String pwd = "123456";

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println(principalCollection.asList().toString());
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addRole("admin");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if (token.getUsername().equals(name)) {
            return new SimpleAuthenticationInfo(name, DecriptUtil.MD5(pwd), getName());
        } else {
            return null;
        }
    }
}
