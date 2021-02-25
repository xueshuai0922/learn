package com.xs.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xueshuai
 * @date 2021/2/14 19:04
 * @description
 */
public class NioServer {
    public static void main(String[] args) throws Exception {
        //open一个服务端channel，绑定端口号
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8000));
        //服务端socketChannel非阻塞
        serverSocketChannel.configureBlocking(false);
        //open一个selector
        Selector selector = Selector.open();
        //监听客户端
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {//这里主要是循环selector
            int channelSize = selector.select(1000);
            if (channelSize == 0) {
                System.out.println("客户端等待连接中");
                continue;//跳出此次循环
            }
            //获取channel中有事件的key(key中也绑定channel)
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //判断key的事件类型
                //如果是连接类型
                if (key.isAcceptable()) {
                    //获取连接客户端
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置非阻塞
                    socketChannel.configureBlocking(false);

                    //channel 注册到selector,监听读事件
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(18));
                }
                if (key.isReadable()) {
                    //监听到读信息，根据key反向获取channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(18);
                    int read = channel.read(byteBuffer);
                    if (read > 0) {
                        System.out.println("接收到的客户端信息： " + new String(byteBuffer.array()));
                    }
                }
            }
            iterator.remove();//防止重复
        }
    }
}
