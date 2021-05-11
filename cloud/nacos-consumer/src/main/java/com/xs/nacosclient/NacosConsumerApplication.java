package com.xs.nacosclient;

import com.xs.nacosclient.openfeign.annotation.EnableXsOpenFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xs")
@EnableXsOpenFeign(basePackages = "com.xs")
public class NacosConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosConsumerApplication.class, args);
	}

}
