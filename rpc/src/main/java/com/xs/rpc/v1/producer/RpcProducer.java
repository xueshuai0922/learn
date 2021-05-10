package com.xs.rpc.v1.producer;

import com.xs.rpc.v1.server.RpcServer;

/**
 * @author xueshuai
 * @date 2021/5/4 13:03
 * @description
 */
public class RpcProducer {

    public static void main(String[] args) throws Exception {
        RpcServer rpcServer = new RpcServer();
        rpcServer.receive();

    }
}
