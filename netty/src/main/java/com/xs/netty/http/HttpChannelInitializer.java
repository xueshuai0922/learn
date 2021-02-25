package com.xs.netty.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author xueshuai
 * @date 2021/2/18 15:37
 * @description
 */
public class HttpChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new HttpServerCodec());
//        //http 聚合器
        pipeline.addLast(new HttpObjectAggregator(8909));
        pipeline.addLast(new NettyHttpHandler());

    }
}
