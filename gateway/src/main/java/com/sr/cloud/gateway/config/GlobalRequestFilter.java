package com.sr.cloud.gateway.config;

import com.sr.cloud.base.dto.CommonResponse;
import com.sr.cloud.base.dto.constant.Constants;
import com.sr.cloud.base.dto.enu.StatusCodeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
public class GlobalRequestFilter implements GlobalFilter, Ordered {
    private static final List<String> WHITE_LIST = Arrays.asList("/u/user/login", "***");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        URI uri = request.getURI();
        // 白名单
        if (WHITE_LIST.contains(uri.getPath())) {
            return chain.filter(exchange);
        }

        List<String> tokenList = request.getHeaders().get(Constants.TOKEN_KEY);
        if (CollectionUtils.isEmpty(tokenList)) {
            ServerHttpResponse serverHttpResponse = exchange.getResponse();
            serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            byte[] bytes = CommonResponse.getInstance(StatusCodeEnum.TOKEN_EMPTY).toString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return exchange.getResponse().writeWith(Flux.just(buffer));
        }

        // todo 根据token从redis或者数据库或者user服务拿用户信息
        String token = tokenList.get(0);
        String userId = "1";

        if (StringUtils.isBlank(userId)) {
            ServerHttpResponse serverHttpResponse = exchange.getResponse();
            serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            byte[] bytes = CommonResponse.getInstance(StatusCodeEnum.TOKEN_ERROR).toString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return exchange.getResponse().writeWith(Flux.just(buffer));
        }

        // 透传 token
        request = request.mutate().header(Constants.USER_KEY, userId).build();
        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

}