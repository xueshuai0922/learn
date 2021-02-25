package com.xs.netty.tcp.fix.fixedlength;

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
public class NettyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        Channel channel = ctx.channel();
        int length = msg.readableBytes();

        System.out.println(msg.toString(CharsetUtil.UTF_8));
        System.out.println();

        channel.writeAndFlush(Unpooled.copiedBuffer("你好,Netty Client,测试粘包拆包", CharsetUtil.UTF_8));


    }
}
