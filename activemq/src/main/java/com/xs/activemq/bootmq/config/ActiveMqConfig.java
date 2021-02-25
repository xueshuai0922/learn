package com.xs.activemq.bootmq.config;

import com.xs.activemq.bootmq.util.SpringUtils;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;

/**
 * @author xueshuai
 * @date 2021/2/1 14:15
 * @description mq配置类
 */
@Configuration
@EnableJms
public class ActiveMqConfig {

//    @Resource
//    private ActiveMQConnectionFactory connectionFactory;


    public ActiveMqConfig() {

    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //消息重发机制设置
//        connectionFactory.setRedeliveryPolicy();
        return connectionFactory;
    }


    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory) {

        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
//        jmsTemplate.setReceiveTimeout(5000);
//        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        jmsTemplate.setPriority(9);
//        jmsTemplate.setDeliveryPersistent(false);
//        jmsTemplate.setExplicitQosEnabled(false);
//        jmsTemplate.setSessionTranscteda(false);
//        jmsTemplate.setTimeToLive(4000);
        return jmsTemplate;
    }

    //生产者事务，挂在spring事务下
    @Bean
    public PlatformTransactionManager transactionManager(ActiveMQConnectionFactory connectionFactory) {
        return new JmsTransactionManager(connectionFactory);
    }

}
