package com.xs.activemq.bootmq.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.Queue;

/**
 * @author xueshuai
 * @date 2021/2/1 14:34
 * @description 生产者
 */
@Component
public class QueueProducer {
    @Autowired
    private JmsTemplate jmsTemplate;


    public void send(Queue queue, String message) {
        jmsTemplate.convertAndSend(queue, message);
        System.out.println("队列发送消息成功");
    }
}
