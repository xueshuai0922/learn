package com.xs.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author xueshuai
 * @date 2021/1/14 17:15
 * @description
 */
public class JWTToken implements AuthenticationToken {


    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
