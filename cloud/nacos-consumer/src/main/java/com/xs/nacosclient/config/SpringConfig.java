package com.xs.nacosclient.config;

import feign.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author xueshuai
 * @date 2021/5/5 16:40
 * @description
 */

@Configuration
public class SpringConfig {
    @Bean
    @LoadBalanced //ribbon的负载均衡增加
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    //openfegin日志打印功能
    @Bean
    public Logger.Level logger(){
        return  Logger.Level.FULL;
    }

}
