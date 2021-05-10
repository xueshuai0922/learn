package com.xs.consumer.controller;

import com.xs.consumer.service.OpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueshuai
 * @date 2021/5/6 10:35
 * @description 测试openFeign的controller
 */

@RestController
public class OpenFeignController {

    @Autowired
    private OpenFeignService service;


    @GetMapping("/fegin/getport")
    public String getPort(){
      return service.getPort();
    }
}
