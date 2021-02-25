package com.xs.activemq.bootmq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author xueshuai
 * @date 2021/2/1 14:29
 * @description 队列消费
 */
@Component
public class TopicConsumer2 {


    @JmsListener(destination = "boot-topic")
    public void receive(String message) {
        System.out.println("注解监听topic2 消费: " + message);

    }
}
