package com.xs.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author xueshuai
 * @date 2021/6/12 13:51
 * @description 全局过滤器
 */

public class CustomGlobalFilter  implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        System.out.println("---------进入全局过滤器中------------");
        return   chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
