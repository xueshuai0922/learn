package com.xs.activemq.bootmq.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author xueshuai
 * @date 2021/2/2 10:13
 * @description
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public SpringUtils() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBeanByName(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBeanByType(Class clazz) {
        return (T) applicationContext.getBean(clazz);
    }
}
