package com.xs.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author xueshuai
 * @date 2021/2/13 19:35
 * @description 网络nio测试  telnet 127.0.0.1 9000
 */
public class NioSocketTest2 {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9000));


        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        SocketChannel clientChannel = serverSocketChannel.accept();
        while (true) {
//            if(clientChannel!=null) {
//                clientChannel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(8);
            //
            while (true) {
                buffer.clear();
                int read = clientChannel.read(buffer);
                if (read == -1) {
                    break;
                } else if (read > 0) {
                    System.out.println(new String(buffer.array()));
                }
            }
//            }


        }

    }
}
