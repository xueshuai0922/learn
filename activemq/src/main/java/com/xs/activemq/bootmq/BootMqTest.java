package com.xs.activemq.bootmq;

import com.xs.activemq.bootmq.producer.QueueProducer;
import com.xs.activemq.bootmq.producer.TopicProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author xueshuai
 * @date 2021/2/1 15:50
 * @description
 */
@SpringBootTest
public class BootMqTest {
    @Resource
    private Queue bootQueue;

    @Resource
    private Queue tcQueue;

    @Resource
    private Topic testTopic;

    @Resource
    private QueueProducer queueProducer;
    @Resource
    private TopicProducer topicProducer;

    @Test
    public void queueTest() {

        queueProducer.send(bootQueue, "boot-queue发送的消息");
    }

    @Test
    public void topicTest() {

        topicProducer.send(testTopic, "test-topic发送的消息");
    }


    //生产事务实现
    @Test
    @Transactional
    public void queueTcTest() {
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
                int j = 10 / 0;
            }
            queueProducer.send(tcQueue, "事务：boot-queue发送的消息");
        }

    }

}
