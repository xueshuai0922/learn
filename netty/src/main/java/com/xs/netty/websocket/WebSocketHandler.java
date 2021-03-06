package com.xs.netty.websocket;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xueshuai
 * @date 2021/2/19 9:31
 * @description
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        System.out.println(ctx.handler().hashCode());
        System.out.println(this.hashCode());
        System.out.println("接收到客户端信息： " + msg.text());
        //回写给客户端
        ArrayList<Object> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Map<Object, Object> map = new HashMap<>();
            map.put("age","12");
            map.put("name","xs");
            map.put("sex","male");
            list.add(map);
        }
        JSON parse = JSONUtil.parse(list);
        ctx.channel().writeAndFlush(new TextWebSocketFrame("接收到客户端消息： " + parse));
    }

    //心跳处理
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            switch (state) {
                case READER_IDLE:
                    System.out.println("读空闲，需要客户端重新连接");
                    break;
                case WRITER_IDLE:
                    System.out.println("写空闲，需要发送心跳包");
                    ctx.writeAndFlush(new TextWebSocketFrame("心跳。。。"));
                    break;
                case ALL_IDLE:
                    System.out.println("读写都空闲");
                    break;
                default: 
                    break;
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    //异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
