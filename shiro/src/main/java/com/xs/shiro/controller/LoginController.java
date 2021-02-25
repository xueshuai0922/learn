package com.xs.shiro.controller;

import com.xs.shiro.common.CommonResult;
import com.xs.shiro.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xueshuai
 * @date 2021/1/8 9:58
 * @description
 */

@RestController
public class LoginController {


    @Resource
    private LoginService loginService;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("login")
    public CommonResult login(String username, String password) {
        return loginService.login(username, password);
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("register")
    public CommonResult register(String username, String password) {
        return loginService.register(username, password);
    }
}
