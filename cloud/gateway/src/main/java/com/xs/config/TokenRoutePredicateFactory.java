package com.xs.config;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author xueshuai
 * @date 2021/6/13 13:25
 * @description  自定义路由判断器  请求参数必须携带token值  这里要注意TokenXXXX Token要和Predicate下的名称一致
 */
@Component
public class TokenRoutePredicateFactory extends AbstractRoutePredicateFactory<TokenRoutePredicateFactory.TokenConfig> {

    /**
     * 将配置文件中Predicate 下token的值赋值到TokenConfig的tokenValue上去
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("tokenValue");
    }

    public TokenRoutePredicateFactory() {
        super(TokenConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(TokenConfig config) {
        return (exchange)->{

            String token = exchange.getRequest().getQueryParams().getFirst("token");
            System.out.println(token);

            if(config.getTokenValue().equals(token)){
                return true;
            }

            return false;
        };
    }



    @Data
    public static  class TokenConfig {
        private String tokenValue;
    }

}
