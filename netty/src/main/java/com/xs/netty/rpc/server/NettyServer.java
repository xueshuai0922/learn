package com.xs.netty.rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author xueshuai
 * @date 2021/2/15 19:34
 * @description netty server
 */
public class NettyServer {
    public static void run(String host, int port) throws InterruptedException {
        //bossGroup+workGroup,默认生成cup核数*2的线程数的线程池
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();


        try {
            //启动器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)//bossGroup 进行accept，workGroup进行处理请求
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)//设置队列

                    //TODO 用到责任链模式
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new NettyServerHandler());

                        }
                    })//workGroup 进行处理的Handler类
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
            ChannelFuture channelFuture = serverBootstrap.bind(host, port).sync();//绑定端口,启动
            String address = channelFuture.channel().localAddress().toString();
            System.out.println("服务端:" + address + " 启动成功");
            //channel关闭 使用closeFuture 不要使用close
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
