package com.xs.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author xueshuai
 * @date 2021/2/18 15:24
 * @description 接受浏览器为客户端进行网络IO访问
 */
public class NettyHttpServer {


    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            //启动工具类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)//父线程组（建立连接），子线程组（主要干活的）
                    .channel(NioServerSocketChannel.class)//设置管道的类型
                    .option(ChannelOption.SO_BACKLOG, 2)//设置连接队列数量
                    .childHandler(new HttpChannelInitializer())//设置pipeline中的Handler
                    .childOption(ChannelOption.SO_KEEPALIVE, true);//
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            System.out.println(channelFuture.channel().localAddress() + " 启动成功");

            channelFuture.addListener((future) -> {
                if (channelFuture.isSuccess()) {
                    System.out.println("监听端口8888成功");
                } else {
                    System.out.println("监听端口8888失败");
                }
            });
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();

        }


    }
}
