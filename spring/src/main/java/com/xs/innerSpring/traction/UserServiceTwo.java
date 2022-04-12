package com.xs.innerSpring.traction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xueshuai
 * @date 2022/4/10 18:01
 * @description
 */
@Service
public class UserServiceTwo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateUser_REQUIRES_NEW(){
        //注意： 这里要主要如果propagation = Propagation.REQUIRES_NEW ，那么这里会创建一个新的事务，
        // 新事务和UserServiceOne都更新一样的数据，如果name不是索引，那么mysql会锁住全表（UserServiceOne获取了锁）
        // ，这里的更新就因为无法获取锁而失败，所以应该将name作为【唯一索引】，进行行锁
        try {
            jdbcTemplate.update("update user set age =? where name=?;",2333,"插入");
            int i=1/0;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void updateUser_NESTED(){
        try {
            jdbcTemplate.update("update user set age =? where name=?;",2333,"插入");
            int i=1/0;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
