package com.xs.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;

import javax.lang.model.element.VariableElement;

/**
 * @author xueshuai
 * @date 2021/2/15 19:45
 * @description netty 服务端处理类 继承ChannelInboundHandlerAdapter
 * （适配器，其实就是要遵循它的规范，并在适配的时候，能正常被和使用）
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    //channel通道管理单例对象：  存在map放着channels
    private static final DefaultChannelGroup GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //读取客户端信息,参数1：channel上下文(channel、)；参数2 接收到的msg
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //根据channel上下文获取channel
        Channel channel = ctx.channel();
        String address = channel.remoteAddress().toString();
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("ctx.hashCode : " + ctx.hashCode());
        System.out.println("channel.hashCode : " + channel.hashCode());
        System.out.println("从 " + address + " 接收到的消息： " + byteBuf.toString(CharsetUtil.UTF_8));
        GROUP.forEach((c -> {
            if (c == channel) {
                String ms = "从 【自己】【" + address + "】 接收到的消息： " + byteBuf.toString(CharsetUtil.UTF_8);
                c.writeAndFlush(Unpooled.copiedBuffer(ms.getBytes()));
            } else {
                String ms = "从 【客户端】【" + address + "】 接收到的消息： " + byteBuf.toString(CharsetUtil.UTF_8);
                c.writeAndFlush(Unpooled.copiedBuffer(ms.getBytes()));
            }
        }));


    }


    //读取完成后，进行操作
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channel.flush();


    }


    //异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();//打印异常
        ctx.close();
    }


    //上下线流程 ：handlerAdded--> channelActive--->channelInactive--->handlerRemoved
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive..........");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive..........");

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded..........");
        System.out.println("【客户端】" + ctx.channel().remoteAddress() + "上线了");
        GROUP.add(ctx.channel());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved..........");
        System.out.println("【客户端】" + ctx.channel().remoteAddress() + "下线了");
        GROUP.remove(ctx.channel());

    }
}
