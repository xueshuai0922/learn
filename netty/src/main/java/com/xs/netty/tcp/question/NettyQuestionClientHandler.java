package com.xs.netty.tcp.question;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author xueshuai
 * @date 2021/2/22 10:01
 * @description
 */
public class NettyQuestionClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();


        for (int i = 0; i < 5; i++) {
            channel.writeAndFlush(Unpooled.copiedBuffer("你好，Netty，测试粘包拆包", CharsetUtil.UTF_8));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
