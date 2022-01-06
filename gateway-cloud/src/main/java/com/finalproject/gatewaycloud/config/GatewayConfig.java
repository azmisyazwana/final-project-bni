package com.finalproject.gatewaycloud.config;

import com.finalproject.gatewaycloud.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("user-service", r -> r.path("/users/**").filters(f -> f.filter(filter)).uri("lb://user-service"))
                .route("post-service", r -> r.path("/posts/**").filters(f -> f.filter(filter)).uri("lb://post-service"))
                .route("user-service", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://user-service"))
                .route("user-service", r -> r.path("/admin/**").filters(f -> f.filter(filter)).uri("lb://user-service"))
                .route("post-service", r -> r.path("/categories/**").filters(f -> f.filter(filter)).uri("lb://post-service"))
                .route("post-service", r -> r.path("/likes/**").filters(f -> f.filter(filter)).uri("lb://post-service"))
                .route("post-service", r -> r.path("/comments/**").filters(f -> f.filter(filter)).uri("lb://post-service")).build();
    }
}
