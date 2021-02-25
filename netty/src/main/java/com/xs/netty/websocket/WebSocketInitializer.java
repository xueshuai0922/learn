package com.xs.netty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author xueshuai
 * @date 2021/2/19 9:14
 * @description
 */
public class WebSocketInitializer extends ChannelInitializer {


    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //基于http协议
        pipeline.addLast(new HttpServerCodec());
        //http片段聚合
        pipeline.addLast(new HttpObjectAggregator(8064));
        // ws的server请求路径
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //心跳处理
         /*
        说明
        1. IdleStateHandler 是netty 提供的处理空闲状态的处理器
        2. long readerIdleTime : 表示多长时间没有读, 就会发送一个心跳检测包检测是否连接
        3. long writerIdleTime : 表示多长时间没有写, 就会发送一个心跳检测包检测是否连接
        4. long allIdleTime : 表示多长时间没有读写, 就会发送一个心跳检测包检测是否连接

        5. 文档说明
        triggers an {@link IdleStateEvent} when a {@link Channel} has not performedread, write, or both operation for a while.
        6. 当 IdleStateEvent 触发后 , 就会传递给管道 的下一个handler去处理
        通过调用(触发)下一个handler 的 userEventTiggered , 在该方法中去处理 IdleStateEvent(读空闲，写空闲，读写空闲)
         */
        pipeline.addLast(new IdleStateHandler(2, 50, 70, TimeUnit.SECONDS));
        System.out.println("初始化channel");

        //ws处理类
        pipeline.addLast(new WebSocketHandler());
    }
}
