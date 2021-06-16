package com.xs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueshuai
 * @date 2021/5/24 17:16
 * @description
 */
@RestController
public class GateWayController {

    @GetMapping("/getInfo")
    public String gateWay(){
        System.out.println("进入gateway");
        return "进入";
    }

}
