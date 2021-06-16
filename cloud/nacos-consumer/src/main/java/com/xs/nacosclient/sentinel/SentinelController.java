package com.xs.nacosclient.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.nacos.common.utils.ExceptionUtil;
import com.xs.nacosclient.common.CommonResult;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

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
     * 增加sentinel的注解，value指的是访问资源名称，blockHandler：限流逻辑处理 fallback： 服务熔断，降级处理 对于异常处理的操作（兜底）
     * 注意：埋点方法不支持private
     * @return
     */
    private  int i = 0;
    @SentinelResource(value = "getLotsRequests", blockHandler = "blockHandler", fallback = "exceptionFallback")
    @PostMapping("/getLotsRequests")
    public CommonResult getLotsRequests(){
        try {
//            Thread.sleep(250);
//            int i = 1/0;
            System.out.println("250ms");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }


        return  CommonResult.success("");
    }

    /**
     * 测试热点参数规则
     * @param a
     * @param b
     * @return
     */
    @SentinelResource(value = "getPort", blockHandler = "blockPort", fallback = "exceptionFallback")
    @GetMapping("/getPort")
    public CommonResult getPort(@RequestParam( required = false) String a ,@RequestParam(required = false) String b){
        return  CommonResult.success("");
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public CommonResult exceptionFallback(@RequestParam( required = false) String a ,@RequestParam(required = false) String b,
                                          Throwable throwable) {
        System.out.println("--------exceptionFallback---------");
        return CommonResult.successMsg("异常后熔断fallback ");
    }

    // Block 参数最后多一个 BlockException，其余与原函数一致.
    public CommonResult blockPort(@RequestParam( required = false) String a ,@RequestParam(required = false) String b ,
                                  BlockException ex) {
        // Do some log here.
        System.out.println("------blockHandler-------");
        ex.printStackTrace();
        return CommonResult.successMsg("限流block ");
    }

    // 原函数
    @PostMapping("/hello")
    public String hello() {
        return String.format("Hello at ");
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public CommonResult exceptionFallback(Throwable throwable) {
        System.out.println("--------exceptionFallback---------");
        return CommonResult.successMsg("异常后熔断fallback ");
    }

    // Block 参数最后多一个 BlockException，其余与原函数一致.
    public CommonResult blockHandler( BlockException ex) {
        // Do some log here.
        System.out.println("------blockHandler-------");
        ex.printStackTrace();
        return CommonResult.successMsg("限流block ");
    }

    // 这里单独演示 blockHandlerClass 的配置.
    // 对应的 `handleException` 函数需要位于 `ExceptionUtil` 类中，并且必须为 public static 函数.
    @SentinelResource(value = "test", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
    public void test() {
        System.out.println("Test");
    }

}
