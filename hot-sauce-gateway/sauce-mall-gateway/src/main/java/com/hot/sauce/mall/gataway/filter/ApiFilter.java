package com.hot.sauce.mall.gataway.filter;

import com.hot.sauce.mall.gataway.config.WhiteUrlList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ： coder.Yang
 * @date ： 2021/8/26 16:17
 * @description ：
 */
@Slf4j
@Configuration
public class ApiFilter implements GlobalFilter, Ordered {

    @Autowired
    private WhiteUrlList whiteUrlList;
    /**
     * 白名单过滤
     * token合法性校验
     * token、用户缓存信息校验
     * 请求包装
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //uri
        String uri = request.getURI().getPath();

        whiteUrlList.getUrlList().stream().forEach(s -> System.out.println(s));

        return null;
    }

    @Override
    public int getOrder() {
        return 10001;
    }
}
