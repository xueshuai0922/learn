package com.xs.nacosclient.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.nacos.common.utils.ExceptionUtil;
import com.xs.nacosclient.common.CommonResult;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueshuai
 * @date 2021/5/8 11:26
 * @description Sentinel 测试
 */

@RestController
@RequestMapping("/sentinel")
@RefreshScope
public class SentinelController {


    /**
     * postman或者jmeter 进行多线程测试
     * 增加sentinel的注解，value指的是访问资源名称，blockHandler：限流逻辑处理 fallback： 服务熔断，对于异常处理的操作（兜底）
     * 注意：埋点方法不支持private
     * @return
     */
    @SentinelResource(value = "getLotsRequests", blockHandler = "exceptionHandler", fallback = "helloFallback")
    @PostMapping("/getLotsRequests")
    public CommonResult getLotsRequests(){



        return  CommonResult.success("");
    }

    // 原函数
    @PostMapping("/hello")
    @SentinelResource(value = "hello", blockHandler = "exceptionHandler", fallback = "helloFallback")
    public String hello(long s) {
        return String.format("Hello at %d", s);
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public String helloFallback(long s ,Throwable throwable) {
        return String.format("Halooooo %d", s);
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String exceptionHandler(long s, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return "Oops, error occurred at " + s;
    }

    // 这里单独演示 blockHandlerClass 的配置.
    // 对应的 `handleException` 函数需要位于 `ExceptionUtil` 类中，并且必须为 public static 函数.
    @SentinelResource(value = "test", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
    public void test() {
        System.out.println("Test");
    }

}
