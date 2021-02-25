package com.xs.netty.tcp.fix.delimiter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author xueshuai
 * @date 2021/2/22 13:53
 * @description
 */
public class DelimiterBasedFrameEncoder extends MessageToByteEncoder<String> {
    private String s;

    public DelimiterBasedFrameEncoder(String s) {
        this.s = s;
    }


    //
    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        out.writeBytes(Unpooled.copiedBuffer(msg + s, CharsetUtil.UTF_8));
    }
}
