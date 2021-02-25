package com.xs.shiro.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.xs.shiro.common.CommonResult;
import com.xs.shiro.mapper.LoginMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xueshuai
 * @date 2021/1/8 13:13
 * @description 登录和注册的service
 */
@Service
public class LoginService {
    @Resource
    private LoginMapper loginMapper;

    public CommonResult login(String username, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        //todo 这里token改为jwt token
        //1.先利用jwtutil完成用username和password的token
        //2.设置到登录中的token（jwttoken 实现AuthenticationToken）
        //3.customerRealm 认证方法中进行解密
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            //todo 操作日志记录  成功后进行JWTToken返回
            return CommonResult.success("登录成功");
        } catch (UnknownAccountException e) {
            return CommonResult.fail("用户不存在");
        } catch (IncorrectCredentialsException e) {
            return CommonResult.fail("密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.fail("登录失败");
        }
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    public CommonResult register(String username, String password) {
        //生成随机盐
        String salt = RandomUtil.randomString(RandomUtil.BASE_CHAR, 10);
        Md5Hash md5Hash = new Md5Hash(password, salt, 1024);

        loginMapper.insert(username, md5Hash.toHex(), username, salt);
        return CommonResult.success("插入成功");
    }

}
