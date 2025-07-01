package org.faketri.config;

import org.faketri.filter.FilterConfig;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder,
                                           FilterConfig filterConfig) {
        return builder.routes()
                .route("user_service", r -> r.path("/api/user/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
