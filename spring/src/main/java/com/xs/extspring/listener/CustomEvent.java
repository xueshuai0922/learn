package com.xs.extspring.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @author xueshuai
 * @date 2021/5/10 20:19
 * @description 自定义spring事件
 */

public class CustomEvent extends ApplicationEvent {
    private String eventName;

    public CustomEvent(Object source) {
        super(source);
        this.eventName="com.xs.Custom";
    }

    @Override
    public String toString() {
        return "CustomEvent{" +
                "eventName='" + eventName + '\'' +
                ", source=" + source +
                '}';
    }
}
