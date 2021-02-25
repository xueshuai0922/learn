package com.xs.shiro.mapper;

import com.xs.shiro.common.CommonResult;
import com.xs.shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xueshuai
 * @date 2021/1/8 13:14
 * @description
 */
@Mapper
public interface LoginMapper {
    //注册
    void insert(String username, String password, String userid, String salt);

    //登录验证
    User getUserByUserName(String username);

    //根据userid获取角色


}
