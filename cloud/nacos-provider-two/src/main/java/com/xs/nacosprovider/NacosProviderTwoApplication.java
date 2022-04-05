package com.xs.nacosprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosProviderTwoApplication.class, args);
	}

}
