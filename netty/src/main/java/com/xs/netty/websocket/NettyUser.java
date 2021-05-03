package com.xs.netty.websocket;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author xueshuai
 * @date 2021/4/17 1:03
 * @description
 */
public class NettyUser {

    private ChannelHandlerContext context;
    private String clientId;
    private String clientName;

    public NettyUser(ChannelHandlerContext context, String clientId, String clientName) {
        this.context = context;
        this.clientId = clientId;
        this.clientName = clientName;
    }

    public ChannelHandlerContext getContext() {
        return context;
    }

    public void setContext(ChannelHandlerContext context) {
        this.context = context;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
