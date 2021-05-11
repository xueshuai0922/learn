package com.xs.extspring.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author xueshuai
 * @date 2021/5/10 20:23
 * @description 自定义事件监听（必须要有触发）
 */
@Component
public class CustomApplicationListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof CustomEvent) {
            System.out.println("接到的自定义事件： "+ applicationEvent);
        }
    }
//    @Override
//    public void onApplicationEvent(WebServerInitializedEvent applicationEvent) {
//            System.out.println("接到的自定义事件： "+ applicationEvent);
//    }
}
