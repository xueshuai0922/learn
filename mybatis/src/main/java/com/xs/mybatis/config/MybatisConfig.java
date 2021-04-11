package com.xs.mybatis.config;

import com.xs.mybatis.login.entity.UserBeanFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xueshuai
 * @date 2021/4/10 22:38
 * @description
 */
@Configuration
public class MybatisConfig {


    @Bean
    public TypeHandler customTypeHandler(){
        return  new CustomTypeHandler();
    }

}
