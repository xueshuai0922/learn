package com.xs.nacosclient.controller;

import com.xs.nacosclient.service.OpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueshuai
 * @date 2021/5/6 10:35
 * @description 测试openFeign的controller
 */

/**
 * 测试openFeign
 */
@RestController
public class OpenFeignController {

    @Autowired
    private OpenFeignService service;


    public OpenFeignController() {
        System.out.println("扫描到了---------------");
    }

    @GetMapping("/fegin/getport")
    public String getPort(){
        System.out.println("----------------进入了");
      return service.getPort();
    }
}
