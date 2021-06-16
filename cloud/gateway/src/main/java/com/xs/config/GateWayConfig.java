package com.xs.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author xueshuai
 * @description 路由配置，
 */
@Configuration
public class GateWayConfig {


    /**
     * 手动在类中配置过滤规则
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes().route(
                (p) -> p.path("/helloxxx").filters((url) ->
                        url.stripPrefix(1)
                ).uri("http://www.baidu.com")
        ).build();

    }

    /**
     * 配置全局过滤器(对所有匹配路由，不是所有请求)  for all routes
     *
     * @return
     */
    @Bean
    public GlobalFilter customerFilter() {

        return new CustomGlobalFilter();
    }

    /**
     * 限流过滤器增加，先要引入sentinel和gateway结合的jar
     * @return
     */
    @Bean
    public GlobalFilter SentinelGatewayFilter(){
        return  new SentinelGatewayFilter();
    }

    /**
     * cross全局跨域问题解决
     */
    @Bean
    public CorsFilter crosFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return  new CorsFilter(source);

    }
}
