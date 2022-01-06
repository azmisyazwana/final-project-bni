package com.finalproject.gatewaycloud.filter;

import com.finalproject.gatewaycloud.exception.JwtTokenMalformedException;
import com.finalproject.gatewaycloud.exception.JwtTokenMissingException;
import com.finalproject.gatewaycloud.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Component
@Slf4j
public class JwtAuthenticationFilter implements GatewayFilter {

    String JWT_HEADER_NAME = "Authorization";
    String JWT_PREFIX = "Bearer ";

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        final List<String> apiEndpoints = List.of("/register", "/login");

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (isApiSecured.test(request)) {
            String token = getJwtTokenFromRequest(request);

            if (token != null) {
                try {
                    jwtUtil.validateToken(token);
                } catch (JwtTokenMalformedException | JwtTokenMissingException e) {
                    System.out.println(e.getMessage());
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.BAD_REQUEST);

                    return response.setComplete();
                }

                Claims claims = jwtUtil.getClaims(token);
                exchange.getRequest().mutate().
                        header("id", String.valueOf(claims.get("id")))
                        .build();
            } else {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        }

        return chain.filter(exchange);
    }

    public String getJwtTokenFromRequest(ServerHttpRequest request){
        String bearerToken = request.getHeaders().getFirst(JWT_HEADER_NAME);
        if(bearerToken != null && bearerToken.startsWith(JWT_PREFIX)){
            return bearerToken.substring(JWT_PREFIX.length());
        }
        return null;
    }


}
