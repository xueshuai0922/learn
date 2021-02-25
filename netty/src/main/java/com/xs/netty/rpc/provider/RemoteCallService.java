package com.xs.netty.rpc.provider;

import com.xs.netty.rpc.RemoteCallInterface;

/**
 * @author xueshuai
 * @date 2021/2/24 10:05
 * @description
 */
public class RemoteCallService implements RemoteCallInterface {
    @Override
    public String handle(String para) {
        System.out.println("RPC----具体实现方法");
        return "接收到消息处理后：" + para;
    }
}
