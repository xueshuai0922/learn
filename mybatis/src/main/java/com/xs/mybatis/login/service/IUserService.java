package com.xs.mybatis.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xs.mybatis.common.CommonResult;
import com.xs.mybatis.login.entity.User;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    CommonResult selectOne(String id);

    CommonResult selectChoose(User user);

     CommonResult selectByPage();

     CommonResult deleteBatch(String  ids);

     CommonResult insert();
}
