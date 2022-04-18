package com.xs;

import com.xs.service.TestInterface;
import com.xs.spring.ComponentScan;
import com.xs.spring.SpringApplication;

/**
 * @author xueshuai
 * @date 2022/4/17 20:33
 * @description
 *  1.扫描注解ComponentScan，进行遍历对应的文件夹下的类
 *  2.类中有Component的注解，进行实例化
 *
 */
@ComponentScan(value = "com.xs.service")
public class XueshuaiSpringApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(XueshuaiSpringApplication.class);
//        System.out.println(springApplication.getBean("userService"));
//        System.out.println(springApplication.getBean("userService"));
        TestInterface orderService = (TestInterface)springApplication.getBean("orderService");
        orderService.test();
//        System.out.println(orderService);
//        System.out.println(springApplication.getBean("userServices"));

    }
}
