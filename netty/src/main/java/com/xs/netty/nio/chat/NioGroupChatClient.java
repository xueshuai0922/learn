package com.xs.netty.nio.chat;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author xueshuai
 * @date 2021/2/15 11:16
 * @description 群聊客户端
 */
public class NioGroupChatClient {

    private SocketChannel socketChannel;
    private Selector selector;
    private String clientName;

    public NioGroupChatClient() throws IOException {
        socketChannel = socketChannel.open(new InetSocketAddress(8090));
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        //客户端监听读事件，读取其他客户端发的消息
        socketChannel.register(selector, SelectionKey.OP_READ);
        clientName = socketChannel.getRemoteAddress().toString().substring(1);


    }

    //发送消息
    public void write(String info) throws IOException {
        info = clientName + "说：" + info;
        socketChannel.write(ByteBuffer.wrap(info.getBytes()));
    }

    //接收消息
    public void read() throws IOException {
        while (true) {
            int select = selector.select(2000);
            if (select > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        String address = channel.getRemoteAddress().toString().substring(1);
                        channel.configureBlocking(false);
                        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
                        int read = channel.read(byteBuffer);
                        if (read != -1) {
                            System.out.println("收到来自" + address + "的消息： " + new String(byteBuffer.array()));
                        }
                    }
                }
                iterator.remove();
            }

        }

    }

    public static void main(String[] args) throws IOException {
        NioGroupChatClient nioGroupChatClient = new NioGroupChatClient();
        new Thread(() -> {
            try {
                nioGroupChatClient.read();
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            nioGroupChatClient.write(s);
        }

    }
}
