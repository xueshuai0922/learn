package com.xs.shiro;

import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author xueshuai
 * @date 2021/1/10 15:00
 * @description
 */
public class ShiroMd5Realm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String current = (String) token.getPrincipal();

        //后续可以根据数据库、缓存来查询
        if ("xueshuai".equals(current)) {
            return new SimpleAuthenticationInfo(current, "9c3b5c0672cd599ccf1019bddaa8089b",
                    ByteSource.Util.bytes("123"), this.getName());
        }

        return null;
    }
}
