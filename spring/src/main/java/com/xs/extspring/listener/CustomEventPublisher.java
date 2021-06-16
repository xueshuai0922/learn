package com.xs.extspring.listener;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author xueshuai
 * @date 2021/5/10 20:32
 * @description 自定义事件触发器
 */
@Component
public class CustomEventPublisher  implements ApplicationContextAware , InitializingBean {

    private  ApplicationContext applicationContext;


    @Autowired
    private ApplicationEventPublisher publisher;


    public CustomEventPublisher(){
        //构造-->autowired--->InitializingBean
        System.out.println("CustomEventPublisher--------------构造");
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("---------InitializingBean-------");
        this.applicationContext.publishEvent(new CustomEvent("xs"));


        System.out.println("-----------ApplicationEventPublisher 发布事件---------------");
        publisher.publishEvent(new CustomEvent("ApplicationEventPublisher"));
    }
}
