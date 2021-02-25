package com.xs.netty.rpc.customer;

import com.xs.netty.rpc.RemoteCallInterface;
import com.xs.netty.rpc.client.NettyClient;

/**
 * @author xueshuai
 * @date 2021/2/24 9:38
 * @description
 */
public class CustomerTest {
    public static void main(String[] args) throws InterruptedException {

        RemoteCallInterface remoteCallInterface = (RemoteCallInterface) NettyClient.getBean(RemoteCallInterface.class);
        System.out.println("----------------------start");

        for (; ; ) {
            Thread.sleep(10 * 1000);
            String result = remoteCallInterface.handle("RPC消费端消费消息");
            System.out.println("消费者接收到服务端的消息：  ----》" + result);
        }


    }
}
