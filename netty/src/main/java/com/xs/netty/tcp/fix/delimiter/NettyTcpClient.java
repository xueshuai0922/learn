package com.xs.netty.tcp.fix.delimiter;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author xueshuai
 * @date 2021/2/22 9:56
 * @description
 */
public class NettyTcpClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();


        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class)
                    .group(group)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            String s = ";";
                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new FixedLengthFrameDecoder(38));
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new DelimiterBasedFrameEncoder(s));

                            //接收时候进行
                            pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer(s, CharsetUtil.UTF_8)));

                            pipeline.addLast(new StringDecoder());

//                            // 这里将LengthFieldBasedFrameDecoder添加到pipeline的首位，因为其需要对接收到的数据
//                            // 进行长度字段解码，这里也会对数据进行粘包和拆包处理
//                            pipeline.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));
//                            // LengthFieldPrepender是一个编码器，主要是在响应字节数据前面添加字节长度字段
//                            pipeline.addLast(new LengthFieldPrepender(2));

                            pipeline.addLast(new NettyClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8090).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            group.shutdownGracefully();
        }


    }
}
