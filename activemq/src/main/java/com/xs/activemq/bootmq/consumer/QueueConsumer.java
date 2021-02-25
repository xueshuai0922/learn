package com.xs.activemq.bootmq.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.soap.Text;

/**
 * @author xueshuai
 * @date 2021/2/1 14:29
 * @description 队列消费
 */
@Component
public class QueueConsumer {


    @JmsListener(destination = "boot-queue")
    public void receive(String message) {
        System.out.println("注解监听消费: " + message);

    }

    @JmsListener(destination = "tc-queue")
    public void receiveTc(String message, Session session) throws JMSException {
        try {
            int i = 10 / 0;
            System.out.println("注解监听消费: " + message);
            session.commit();
        } catch (Exception e) {
            //消费异常，进行事务回滚，mq会重发6次（默认）消息进行再次消费，如果还是消费异常，那么进行死信队列
            session.rollback();
            e.printStackTrace();
        }

    }
}
