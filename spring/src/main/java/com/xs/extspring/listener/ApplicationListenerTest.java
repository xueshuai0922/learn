package com.xs.extspring.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author xueshuai
 * @date 2021/5/10 20:03
 * @description  applicationListener 测试
 */
@Component
public class ApplicationListenerTest implements ApplicationListener {

    /**
     * 当容器中事件触发时候，spring会调用,spring默认有一些容器相关的触发
     * @param applicationEvent
     */
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        System.out.println("接收到的事件："+applicationEvent);
    }
}
