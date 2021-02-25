package com.xs.netty.rpc.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.Callable;

/**
 * @author xueshuai
 * @date 2021/2/18 10:12
 * @description netty客户端处理器
 */
public class NettyClientHandler extends SimpleChannelInboundHandler implements Callable {


    private ChannelHandlerContext context;
    private Object message;
    private Object result;

    public NettyClientHandler() {
    }

    ;

    public NettyClientHandler(Object message) {
        this.message = message;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        result = msg;
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
        //唤醒call()中的wait()
        notify();

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ChannelHandlerContext: " + ctx.hashCode());
        context = ctx;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    //调用callable的call方法，来实现远程调用操作，先发送消息---》等待唤醒---》接收消息进行返回
    @Override
    public Object call() throws Exception {
        System.out.println("进行调用call方法");
        context.writeAndFlush(message);
        wait();


        return result;
    }
}
