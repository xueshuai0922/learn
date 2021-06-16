package com.xs.mybatis.login.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xs.mybatis.common.CommonResult;
import com.xs.mybatis.login.entity.User;
import com.xs.mybatis.login.mapper.UserMapper;
import com.xs.mybatis.login.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xueshuai
 * @since 2021-04-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public CommonResult select() {
        List<User> userList = userMapper.select();
        List<User> select = userMapper.select();
        return CommonResult.success(userList);
    }

    @Override
    public CommonResult selectOne(String id) {
        User user = userMapper.selectOne(id);
        return CommonResult.success(user);
    }

    @Override
    public CommonResult selectChoose(User user) {
        User users = userMapper.selectChoose(user);
        return CommonResult.success(users);
    }
    @Override
    public CommonResult selectByPage(){
        //第几页，每页多少条数据
        PageHelper.startPage(0,2);
        PageInfo<User> userPageInfo = new PageInfo<>(userMapper.select());
        return CommonResult.success(userPageInfo);
    }

    @Override
    public CommonResult deleteBatch(String ids) {

        String[] split = ids.split(",");
        if(split.length==0) return null;
        userMapper.deleteBatch(Arrays.asList(split));
        return CommonResult.success("success");
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,Error.class},propagation = Propagation.REQUIRED)
    public CommonResult insert() {
        try {
            for (int i = 0; i < 100; i++) {
                User user = new User();
                user.setId((long)i);
                user.setName("外部事务");
                user.setAge(0);
                user.setEmail("ssdd");
                userMapper.insert(user);
            }
//            insertInner();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    //测试事务、
    @Transactional(rollbackFor = {Exception.class,Error.class},propagation = Propagation.REQUIRED)
    public void insertInner(){
        User user = new User();
        user.setId(0L);
        user.setName("内部事务");
        user.setAge(0);
        user.setEmail("");

        userMapper.insert(user);
    }
}
