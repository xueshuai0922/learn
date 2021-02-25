package com.xs.netty.rpc.provider;

import com.xs.netty.rpc.server.NettyServer;

/**
 * @author xueshuai
 * @date 2021/2/24 9:32
 * @description
 */
public class SeverBootStrap {
    public static void main(String[] args) throws InterruptedException {
        NettyServer.run("127.0.0.1", 8090);
    }
}
