package com.xs.activemq.nativemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.JmsProperties;

import javax.jms.*;
import javax.lang.model.element.VariableElement;

/**
 * @author xueshuai
 * @date 2021/2/1 10:05
 * @description
 */
public class ActiveMqTopicProducer {
    private static final String TOPICNAME = "topic";
    private static final String URL = "tcp://127.0.0.1:61616";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPICNAME);
        MessageProducer producer = session.createProducer(topic);
        for (int i = 0; i < 4; i++) {
            TextMessage textMessage = session.createTextMessage("topic 信息第" + i + "个");
            producer.send(textMessage);
        }
        session.commit();
        session.close();
        connection.close();
        System.out.println("生产者生成消息成功");

    }
}
