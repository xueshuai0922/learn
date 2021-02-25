package com.xs.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author xueshuai
 * @date 2021/2/14 20:37
 * @description nio 客户端
 */
public class NioClient {
    public static void main(String[] args) throws Exception {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8000);
        boolean connect = channel.connect(address);
        if (!connect) {
            while (!channel.finishConnect()) {
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其它工作..");
            }

        }
        String txt = "30's sorriness";
        ByteBuffer byteBuffer = ByteBuffer.wrap(txt.getBytes());
        channel.write(byteBuffer);
        System.in.read();

    }
}
