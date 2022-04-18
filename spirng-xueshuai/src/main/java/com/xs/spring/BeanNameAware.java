package com.xs.spring;

/**
 * @author xueshuai
 * @date 2022/4/17 23:26
 * @description 在
 * @see  SpringApplication # aware
 */
public interface BeanNameAware {

    void  setBeanName(String beanName);
}
