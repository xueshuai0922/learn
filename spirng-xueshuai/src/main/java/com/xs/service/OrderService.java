package com.xs.service;

import com.xs.spring.Autowire;
import com.xs.spring.BeanNameAware;
import com.xs.spring.Component;
import com.xs.spring.InitializingBean;

/**
 * @author xueshuai
 * @date 2022/4/17 20:46
 * @description
 */
@Component
public class OrderService implements BeanNameAware, InitializingBean, TestInterface{

    @Autowire
    private  UserService userService;


    public OrderService() {
        System.out.println("【OrderService-实例化】");
    }

    private String beanName;

    public  void test(){
        System.out.println("OrderService中 autowired 字段 自动注入的bean ："+userService);
        System.out.println("通过BeanAware获取的BeanName为"+beanName);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("【OrderService-初始化方法】 afterPropertiesSet");
    }
}
