package com.xs.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xueshuai
 * @date 2021/5/6 10:41
 * @description  feign的客户端，进行调用使用。给接口，此时接口中的方法要和provider 中提供的方法一致
 */

@FeignClient(name = "provider") //name是指的provider的应用名 （配置中的spring.application.name）
public interface OpenFeignService {

    @GetMapping("/getPort")
    String getPort();

}
