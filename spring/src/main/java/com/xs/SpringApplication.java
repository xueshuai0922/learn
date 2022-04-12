package com.xs;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author xueshuai
 * @date 2021/5/10 20:03
 * @description
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class,args);
    }


}
