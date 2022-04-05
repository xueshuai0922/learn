package com.xs.wechat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author xueshuai
 * @date 2022/4/5 18:26
 * @description
 */

@Configuration
public class RestTemplateConfig {


    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }

}
