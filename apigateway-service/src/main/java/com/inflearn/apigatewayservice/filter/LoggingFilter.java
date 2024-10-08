package com.inflearn.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    public LoggingFilter() {
        super(Config.class);
    }

    @Data
    public static class Config {
//        Put the configuration properties
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

    @Override
    public GatewayFilter apply(Config config) {
//        Custom Pre Filter
//        return ((exchange, chain) -> {
//            ServerHttpRequest request =  exchange.getRequest();
//            ServerHttpResponse response =  exchange.getResponse();
//
////            기본 메세지
//            log.info("Global Filter baseMessage: {}", config.getBaseMessage());
////            Pre Filter
//            if(config.isPreLogger()){
//                log.info("Global Filter Start: request id -> {}", request.getId());
//            }
////            Custom POST Filter
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                if(config.isPostLogger()){
//                    log.info("Global Filter End: response code -> {}", response.getStatusCode());
//                }
//            }));
//        });
        GatewayFilter filter = new OrderedGatewayFilter((exchange, chain)->{
            ServerHttpRequest request =  exchange.getRequest();
            ServerHttpResponse response =  exchange.getResponse();

//            기본 메세지
            log.info("Global Filter baseMessage: {}", config.getBaseMessage());
//            Pre Filter
            if(config.isPreLogger()){
                log.info("Logging Filter : request id -> {}", request.getId());
            }
//            Custom POST Filter
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (config.isPostLogger()) {
                    log.info("Logging Filter: response code -> {}", response.getStatusCode());
                }
            }));
//            우선순위 가장 높게 설정
//        }, Ordered.HIGHEST_PRECEDENCE);
//            우선순위 가장 낮게 설정
        }, Ordered.LOWEST_PRECEDENCE);

        return filter;
    }
}