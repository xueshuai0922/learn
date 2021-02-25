package com.xs.netty.rpc.client;

import com.xs.netty.rpc.RemoteCallInterface;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.lang.reflect.Proxy;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xueshuai
 * @date 2021/2/18 10:05
 * @description netty客户端
 */
public class NettyClient {
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static NettyClientHandler clientHandler;

    public static Object getBean(final Class<?> clazz) {

        Object instance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{clazz},
                (proxy, method, arg) -> {
                    System.out.println("动态代理调用。。。");

                    if (clientHandler == null) {
                        run();
                    }
                    //动态代理对象每次进行调用触发
                    Object o = executor.submit(clientHandler).get();
                    return o;
                });
        return instance;
    }


    public static void run() {
        clientHandler = new NettyClientHandler();
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(clientHandler);
                        }
                    });
            //
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8090).sync();


            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String next = scanner.next();
                Channel channel = channelFuture.channel();
                channel.writeAndFlush(Unpooled.copiedBuffer(next.getBytes()));
            }


            //  Wait until the connection is closed. 连接结束之前一直等待
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            clientGroup.shutdownGracefully();

        }


    }
}
