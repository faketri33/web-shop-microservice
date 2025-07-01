package org.faketri.infastructure.secure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class WebSecure {

    private final JwtAuthFilterChains jwtAuthFilterChains;

    public WebSecure(JwtAuthFilterChains jwtAuthFilterChains) {
        this.jwtAuthFilterChains = jwtAuthFilterChains;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.cors(cors -> cors.configurationSource(request -> corsConfiguration()))
                .authorizeExchange(
                        exchanges ->
                                exchanges
                                        .pathMatchers("/api/user/").permitAll()
                                        .anyExchange().authenticated())
                .addFilterAt(jwtAuthFilterChains, SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }

    @Bean
    public CorsConfiguration corsConfiguration() {
        var corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOriginPatterns(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"
        ));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }
}
