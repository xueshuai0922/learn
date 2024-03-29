package com.xs.innerSpring.traction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xueshuai
 * @date 2022/4/10 18:01
 * @description 测试事务传播机制
 *     REQUIRED(0),
 *     SUPPORTS(1),
 *     MANDATORY(2),
 *     REQUIRES_NEW(3),
 *     NOT_SUPPORTED(4),
 *     NEVER(5),
 *     NESTED(6);
 */
@Service
public class UserServiceOne {
    @Autowired
    UserServiceTwo userServiceTwo;
    @Autowired
    JdbcTemplate jdbcTemplate;


    public UserServiceOne() {
        System.out.println("创建UserServiceOne");
    }

    //    @Transactional(readOnly = true) readOnly 只读属性，保证只是查询，修改时就会报错
    @Transactional()
    public void  updateUser(){
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"D:\\CGLIB");
        jdbcTemplate.update("update user set age =? where name=?;",10,"Tom");
//        userServiceTwo.updateUser_REQUIRES_NEW();
        userServiceTwo.updateUser_NESTED();//外部事务回滚，则嵌套子事务也会回滚，子事务不影响外部事务
//        int i=1/0;
    }
}
