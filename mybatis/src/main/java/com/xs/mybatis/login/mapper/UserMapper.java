package com.xs.mybatis.login.mapper;

import com.xs.mybatis.login.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xueshuai
 * @since 2021-04-08
 */
public interface UserMapper extends BaseMapper<User> {
        List<User> select();
        User selectOne(String id);
        User selectChoose(User user);
        void deleteBatch(List<String> list);
        void insertBatch(List<User> list);
        void updateBatch(List<User> list);
}
