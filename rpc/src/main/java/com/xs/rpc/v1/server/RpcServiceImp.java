package com.xs.rpc.v1.server;

import com.xs.rpc.v1.RpcService;
import com.xs.rpc.v1.dto.User;

/**
 * @author xueshuai
 * @date 2021/5/3 14:26
 * @description
 */
public class RpcServiceImp  implements RpcService {


    @Override
    public String doService(User user) {
        return "服务端接口实现，最终进行rpc远程接口调用真正的实现:\n"+user;
    }
}
