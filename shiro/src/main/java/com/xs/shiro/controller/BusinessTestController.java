package com.xs.shiro.controller;

import com.xs.shiro.common.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueshuai
 * @date 2021/1/29 17:02
 * @description 测试controller
 */
@RestController
public class BusinessTestController {

    @GetMapping("/test")
    public CommonResult test() {
        return CommonResult.success("测试成功了");
    }
}
