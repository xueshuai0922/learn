package com.xs.rpc.v1;

import com.xs.rpc.v1.dto.User;

/**
 * @author xueshuai
 * @date 2021/5/3 14:03
 * @description 消费和生产端需要调用的同一个接口。
 */
public interface RpcService {
    <T> T doService(User user);
}
