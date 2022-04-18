package com.xs.innerSpring.dynamic_datasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author xueshuai
 * @date 2022/4/15 10:45
 * @description 动态数据源配置
 */
//@Configuration
public class DynamicDatasource {


    @Bean
    @ConfigurationProperties("spring.datasource.dsone")
    public  DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }




}
