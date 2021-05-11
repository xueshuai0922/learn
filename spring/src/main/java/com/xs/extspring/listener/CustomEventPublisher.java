package com.xs.extspring.listener;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author xueshuai
 * @date 2021/5/10 20:32
 * @description 自定义事件触发器
 */
@Component
public class CustomEventPublisher  implements ApplicationContextAware , InitializingBean {

    private  ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.applicationContext.publishEvent(new CustomEvent("xs"));
    }
}
