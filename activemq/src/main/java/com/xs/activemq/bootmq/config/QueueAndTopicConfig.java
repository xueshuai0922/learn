package com.xs.activemq.bootmq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author xueshuai
 * @date 2021/2/1 16:02
 * @description 队列和主题的配置类
 */
@Configuration
public class QueueAndTopicConfig {

    @Bean
    public Queue bootQueue() {
        return new ActiveMQQueue("boot-queue");
    }

    @Bean
    public Queue testQueue() {
        return new ActiveMQQueue("test-queue");
    }

    @Bean
    public Queue tcQueue() {
        return new ActiveMQQueue("tc-queue");
    }


    @Bean
    public Topic bootTopic() {
        return new ActiveMQTopic("boot-topic");
    }

    @Bean
    public Topic testTopic() {
        return new ActiveMQTopic("test-topic");
    }
}
