package org.faketri.config;

import org.faketri.filter.FilterConfig;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator authServiceRouteLocator(RouteLocatorBuilder builder,
                                                FilterConfig filterConfig) {
        return builder.routes()
                .route("auth", r -> r.path("/api/auth/**")
                        .uri("http://localhost:8080"))
                .build();
    }

    @Bean
    public RouteLocator userServiceRouteLocator(RouteLocatorBuilder builder,
                                           FilterConfig filterConfig) {
        return builder.routes()
                .route("user_service", r -> r.path("/api/user/**")
                        .uri("http://localhost:8082"))
                .build();
    }

    @Bean
    public RouteLocator ProductServiceRouteLocator(RouteLocatorBuilder builder,
                                                   FilterConfig filterConfig) {
        return builder.routes()
                .route("product_service", r -> r.path("/api/product/**")
                        .uri("http://localhost:8083"))
                .build();
    }

}
