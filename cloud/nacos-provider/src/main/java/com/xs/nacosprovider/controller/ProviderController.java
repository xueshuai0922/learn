package com.xs.nacosprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueshuai
 * @date 2021/5/5 15:30
 * @description
 */

@RestController
public class ProviderController {

    @Value("${server.port}")
    private  int port;

    @GetMapping("/getPort")
    public String getPort(){
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "请求的端口号： "+port;
    }

}
