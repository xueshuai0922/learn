package com.xs.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author xueshuai
 * @date 2021/2/18 15:41
 * @description
 */
public class NettyHttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {


//        Thread.sleep(20000);


        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            Channel channel = ctx.channel();
            //不同客户端连接，不同的
            System.out.println("channel " + channel.hashCode());
            System.out.println("ctx " + ctx.hashCode());
            System.out.println("pipeline " + ctx.channel().pipeline().hashCode());
            System.out.println("handler " + ctx.handler().hashCode());


            //返回给浏览器信息
            ByteBuf content = Unpooled.copiedBuffer("来自Netty服务器的一条消息", CharsetUtil.UTF_8);
            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            //返回是HttpResponse
            ctx.writeAndFlush(fullHttpResponse);

        }

    }
}
