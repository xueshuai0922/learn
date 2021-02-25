package com.xs.activemq.nativemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageConsumer;
import org.apache.activemq.RedeliveryPolicy;

import javax.jms.*;
import java.io.IOException;

/**
 * @author xueshuai
 * @date 2021/1/29 9:55
 * @description mq 消费者
 */
public class ActiveMqQueueConsumer {
    private static final String name = "test.mq";
    private static final String URL = "tcp://127.0.0.1:61616";
    private static final String selector = "key='mq2'";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory(URL);
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(2);
        mqConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        //是否开启异步投递
        mqConnectionFactory.setUseAsyncSend(true);
        Connection connection = mqConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);


        Queue queue = session.createQueue(name);
        //多个方法重载，可以加入jms的选择器进行筛选消费
//        MessageConsumer consumer = session.createConsumer(queue,selector);
        MessageConsumer consumer = session.createConsumer(queue);
        //第一种消费方式 循环receive
        while (true) {
            //消费者进行接收消费
            TextMessage receive = (TextMessage) consumer.receive(1000 * 2);
//            receive.acknowledge();
            if (null != receive) {
                System.out.println("第一个mq消费消息：" + receive.getText());
            } else {
                break;
            }
        }
        //第二种消费方式，监听器
//        consumer.setMessageListener(message -> {
//            TextMessage msg =  (TextMessage)message;
//            try {
//                System.out.println("收到消息 "+msg.getText());
//
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        });

        //让程序一直停止，因为后面的关闭动作比较快，会导致监听消费还没有消费完成就关闭了
//        System.in.read();
        System.out.println("ActiveMq 消费成功");
        connection.close();
        session.close();

    }
}
