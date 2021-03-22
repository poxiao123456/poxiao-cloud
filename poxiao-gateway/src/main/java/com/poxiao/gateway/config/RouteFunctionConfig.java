package com.poxiao.gateway.config;

import com.poxiao.gateway.handle.HystrixFallbackHandler;
import com.poxiao.gateway.handle.ImageCodeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import javax.annotation.Resource;

/**
 * @author qinqi
 * @date 2020/9/11
 * 路由配置信息
 */
@Configuration
public class RouteFunctionConfig {

    @Resource
    private HystrixFallbackHandler hystrixFallbackHandler;
    @Resource
    private ImageCodeHandler imageCodeHandler;

    @Bean
    public RouterFunction<?> routerFunction() {
        return RouterFunctions
                .route(RequestPredicates.path("/fallback").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),hystrixFallbackHandler)
                .andRoute(RequestPredicates.path("/code").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),imageCodeHandler)
                ;
    }
}
