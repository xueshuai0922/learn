package com.xs.netty.tcp.fix.delimiter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author xueshuai
 * @date 2021/2/22 9:55
 * @description
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        System.out.println(msg);
        System.out.println();
        channel.writeAndFlush("你好,Netty Client,测试粘包拆包");


    }
}
