package com.xs.spring;

/**
 * @author xueshuai
 * @date 2022/4/17 23:57
 * @description
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(String beanName,Object bean);
    Object postProcessAfterInitialization(String beanName,Object bean);
}
