package com.xs.mybatis.login.entity;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author xueshuai
 * @date 2021/4/10 23:32
 * @description
 */
@Component
public class UserBeanFactory extends DefaultObjectFactory {

    public UserBeanFactory() {
        System.out.println("实例化UserBeanFactory");
    }

    @Override
    public <T> T create(Class<T> type) {
        System.out.println("---------------------------------------");
        if(type==User.class){
            User user = super.create(User.class);
            user.setName("测试返回结果类自定义");
            user.setAge(0);
            user.setEmail("827681776@qq.com");
            return (T) user;
        }
        return super.create(type);
    }
}
