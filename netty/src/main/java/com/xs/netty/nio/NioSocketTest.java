package com.xs.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * @author xueshuai
 * @date 2021/2/10 22:15
 * @description NIO socket 测试
 */
public class NioSocketTest {
    public static void main(String[] args) throws Exception {

        LinkedList<SocketChannel> linkedList = new LinkedList<>();

        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.bind(new InetSocketAddress(8090));

        while (true) {
            try {
                Thread.sleep(1000);
                SocketChannel client = socketChannel.accept();
                if (client == null) {
                    System.out.println("client is null");
                } else {
                    client.configureBlocking(false);
                    Socket clientSocket = client.socket();
                    int port = clientSocket.getPort();
                    System.out.println("client port :" + port);
                    linkedList.add(client);

                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);

                    for (SocketChannel c : linkedList) {
                        int read = c.read(byteBuffer);
                        if (read > 0) {
                            byteBuffer.flip();
                            byte[] bytes = new byte[byteBuffer.limit()];
                            byteBuffer.get(bytes);
                            System.out.println("输出：" + new String(bytes));
                            byteBuffer.clear();
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
