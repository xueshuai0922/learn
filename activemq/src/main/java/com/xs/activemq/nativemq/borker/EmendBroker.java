package com.xs.activemq.nativemq.borker;

import org.apache.activemq.broker.BrokerService;

/**
 * @author xueshuai
 * @date 2021/1/30 19:45
 * @description 内嵌的ActiveMq服务器
 */
public class EmendBroker {

    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.start();
    }
}
