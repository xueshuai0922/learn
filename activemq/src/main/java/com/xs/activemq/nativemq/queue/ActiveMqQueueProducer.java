package com.xs.activemq.nativemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;

/**
 * @author xueshuai
 * @date 2021/1/29 9:25
 * @description mq 生产者
 */
public class ActiveMqQueueProducer {

    private static final String name = "test.mq";
    private static final String URL = "tcp://127.0.0.1:61616";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = mqConnectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(name);

        MessageProducer producer = session.createProducer(null);
        MessageProducer producer1 = session.createProducer(queue);
//        ActiveMQMessageProducer producer2 = (ActiveMQMessageProducer) session.createProducer(queue);

        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        for (int i = 0; i < 2; i++) {
            TextMessage textMessage = session.createTextMessage("1111:mq" + i);
//            //对队列中的消息增加属性，用于进行消费筛选
//            textMessage.setStringProperty("key","mq"+i);
            producer.send(queue, textMessage);
//            producer2.send(textMessage, new AsyncCallback() {
//                @Override
//                public void onSuccess() {
//
//                }
//
//                @Override
//                public void onException(JMSException exception) {
//
//                }
//            });
        }
        connection.close();
        session.close();
        System.out.println("ActiveMQ 生产者生产成功");
    }


}
