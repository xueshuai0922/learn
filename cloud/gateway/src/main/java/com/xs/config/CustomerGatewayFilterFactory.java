package com.xs.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author xueshuai
 * @date 2021/6/13 12:53
 * @description 自定义gateway过滤器  这里要注意Customer要和配置文件中的filter下名称一致
 */
@Component
public class CustomerGatewayFilterFactory  extends AbstractNameValueGatewayFilterFactory {




    @Override
    public GatewayFilter apply(NameValueConfig config) {



        return (exchange,chain)->{
            System.out.println("key: "+config.getName());
            System.out.println("value: "+config.getValue());
            return  chain.filter(exchange);
        };
    }
}
