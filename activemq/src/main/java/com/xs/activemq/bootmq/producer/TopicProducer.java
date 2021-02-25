package com.xs.activemq.bootmq.producer;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author xueshuai
 * @date 2021/2/1 14:34
 * @description 生产者
 */
@Component
public class TopicProducer {
    @Resource
    private JmsMessagingTemplate jmsTemplate;


    public void send(Topic topic, String message) {
        jmsTemplate.convertAndSend(topic, message);
        System.out.println("队列发送消息成功");
    }
}
