package com.xs.shiro.shiro;

import cn.hutool.core.util.ObjectUtil;
import com.xs.shiro.entity.User;
import com.xs.shiro.mapper.LoginMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashSet;
import java.util.Set;

/**
 * @author xueshuai
 * @date 2021/1/6 14:43
 * @description
 */
public class CustomerRealm extends AuthorizingRealm {

    @Autowired(required = false)
    private LoginMapper loginMapper;

    private final Logger logger = LoggerFactory.getLogger(CustomerRealm.class);

    //用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("进入doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        Set<String> simpleRoles = new HashSet<>();
        HashSet<String> permissions = new HashSet<>();

        simpleAuthorizationInfo.setRoles(simpleRoles);
        simpleAuthorizationInfo.setStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }
    //认证

    /**
     * @param token
     * @return AuthenticationInfo 返回主体认证信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("进入doGetAuthenticationInfo");
        String currentName = (String) token.getPrincipal();
        logger.info("身份信息 principal: " + currentName);
        User user = loginMapper.getUserByUserName(currentName);
        if (ObjectUtil.isNotNull(user)) {
            String password = user.getPassword();
            String salt = user.getSalt();
            String username = user.getUsername();
            // 进行密码验证
            if (username.equals(currentName)) {
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,
                        password, new MySimpleByteSource(salt), this.getName());
                return simpleAuthenticationInfo;
            }
        }

        return null;


    }
}
