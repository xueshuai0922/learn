package com.xs.activemq.nativemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author xueshuai
 * @date 2021/2/1 10:52
 * @description topic 消费者
 */
public class ActiveMqTopicConsumer {
    private static final String TOPICNAME = "topic";
    private static final String URL = "tcp://127.0.0.1:61616";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPICNAME);
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener((message -> {
            TextMessage msg = (TextMessage) message;
            try {
                System.out.println(msg.getText());

            } catch (JMSException e) {
                e.printStackTrace();
            }
        }));
        session.commit();
        System.in.read();
        session.close();
        connection.close();
        System.out.println("topic 消费者消费成功");

    }

}
