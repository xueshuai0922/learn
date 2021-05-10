package com.xs.nacosclient.controller;

import com.xs.nacosclient.common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author xueshuai
 * @date 2021/5/5 15:41
 * @description
 */

@RestController
@RefreshScope //配置自动刷新
public class ConsumerController {


    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancerClient loadBalanced;

    @GetMapping("/getPortByNormal")
    public Object getPort() {

        List<String> services = discoveryClient.getServices();
        services.forEach(System.out::println);

        List<ServiceInstance> provider = discoveryClient.getInstances("naocs-provider");
        ServiceInstance provider1 = loadBalanced.choose("naocs-provider");
        provider.forEach(System.out::println);
        URI uri = provider.get(0).getUri();
        String host = provider.get(0).getHost();
        int port = provider.get(0).getPort();
        String url = "http://" + host + ":" + port + "/getPort";
        //此时调用时候 restTemplate 没有@loadbalanced注解
        return restTemplate.getForObject(url, String.class, (Object) null);
    }


    @GetMapping("/getPortByRobbin")
    public String getPortByRobbin() {

        String url = "http://naocs-provider/getPort";
        //此时增加注解@loadbalanced
        return restTemplate.getForObject(url, String.class);
    }


    /**
     * nacos配置测试，在配置中心 增加一个配置信息
     *    dataId：${application.name}-${profile-active}
     *    页面中选择yaml 或者properties
     *
     *
     */
    @Value("${xs:xueshuai}")
    private String nacosInfo;

    @PostMapping("/getconfig")
    public CommonResult getConfig()
    {
        return  CommonResult.success(nacosInfo);
    }


}
