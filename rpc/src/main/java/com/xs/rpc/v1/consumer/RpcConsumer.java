package com.xs.rpc.v1.consumer;

import com.xs.rpc.v1.RpcService;
import com.xs.rpc.v1.client.RpcClient;
import com.xs.rpc.v1.dto.User;

/**
 * @author xueshuai
 * @date 2021/5/4 11:58
 * @description
 */
public class RpcConsumer  {

    public static void main(String[] args) {
        //拿到接口代理类，进行远程调用（就像本地调用）
        RpcClient rpcClient = new RpcClient();
        RpcService rpcService = rpcClient.getBean(RpcService.class);
        User xs = new User(18, "xs");
        Object o = rpcService.doService(xs);
        System.out.println("客户端远程调用得到结果：  "+o);
    }




}
