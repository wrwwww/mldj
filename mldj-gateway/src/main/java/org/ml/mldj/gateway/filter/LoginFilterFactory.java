package org.ml.mldj.gateway.filter;

import org.ml.mldj.gateway.config.AuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@EnableConfigurationProperties(AuthProperties.class)
public class LoginFilterFactory extends AbstractGatewayFilterFactory {

    final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    AuthProperties authProperties;

    @Override
    public GatewayFilter apply(Object config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();
                // 2.判断是否不需要拦截
                if (isExclude(request.getPath().toString())) {
                    // 无需拦截，直接放行
                    return chain.filter(exchange);
                }
                List<String> authorization = request.getHeaders().get("Authorization");
                String token = null;
                if (authorization != null && !authorization.isEmpty()) {
                    token = authorization.get(0);
                }

                return chain.filter(exchange);
            }
        };
    }

    private boolean isExclude(String path) {
        for (String pathPattern : authProperties.getExcludePaths()) {
            if (antPathMatcher.match(pathPattern, path)) {
                return true;
            }
        }
        return false;
    }

}
