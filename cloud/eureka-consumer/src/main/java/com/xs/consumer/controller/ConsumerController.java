package com.xs.consumer.controller;

import com.xs.consumer.config.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        List<ServiceInstance> provider = discoveryClient.getInstances("provider");
        ServiceInstance provider1 = loadBalanced.choose("provider");
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

        String url = "http://provider/getPort";
        //此时增加注解@loadbalanced
        return restTemplate.getForObject(url, String.class);
    }


    @Autowired
    private HealthService healthService;

    //主动上传客户端状态给eureka注册中心
    @GetMapping("/setEurekaStatus/{status}")
    public Boolean setStatus(@PathVariable boolean status) {
        healthService.setStatus(status);

        return healthService.getStatus();
    }
}
