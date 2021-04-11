package com.xs.mybatis.login.service;

import com.xs.mybatis.common.CommonResult;
import com.xs.mybatis.login.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xueshuai
 * @since 2021-04-08
 */
public interface IUserService extends IService<User> {

    CommonResult select();

    CommonResult selectOne(String id);

    CommonResult selectChoose(User user);

     CommonResult selectByPage();

     CommonResult deleteBatch(String  ids);
}