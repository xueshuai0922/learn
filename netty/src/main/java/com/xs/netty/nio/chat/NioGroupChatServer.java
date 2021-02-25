package com.xs.netty.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xueshuai
 * @date 2021/2/15 9:34
 * @description 用nio打造一个群聊的服务端
 */
public class NioGroupChatServer {


    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private SocketChannel socketChannel;

    public NioGroupChatServer() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8090));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    //监听
    public void listen() throws IOException {
        while (true) {
            int select = selector.select();
            if (select > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(20));
                        String address = socketChannel.getRemoteAddress().toString();
                        System.out.println(address + "上线了");
                    }
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        String remoteAddress = channel.getRemoteAddress().toString();
                        //接收客户端消息
                        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
                        int read = channel.read(byteBuffer);
                        if (read != -1) {
                            System.out.println("接收客户端：" + remoteAddress + "  消息： " + new String(byteBuffer.array()));
                        }
                        byteBuffer.flip();
                        //转发给其他在线的channel
                        for (SelectionKey k : selector.keys()) {
                            SelectableChannel selectableChannel = k.channel();
                            selectableChannel.configureBlocking(false);
                            if (selectableChannel instanceof SocketChannel && selectableChannel != channel) {
                                ((SocketChannel) selectableChannel).write(byteBuffer);
                            }
                        }


                    }
                }
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NioGroupChatServer server = new NioGroupChatServer();
        server.listen();
    }


}
