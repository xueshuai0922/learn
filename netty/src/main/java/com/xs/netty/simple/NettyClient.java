package com.xs.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.BootstrapConfig;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author xueshuai
 * @date 2021/2/18 10:05
 * @description netty客户端
 */
public class NettyClient {

    public static void main(String[] args) {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            //
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9999).sync();


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
