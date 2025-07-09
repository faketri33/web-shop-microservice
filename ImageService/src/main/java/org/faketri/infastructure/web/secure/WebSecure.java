package org.faketri.infastructure.web.secure;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.lang.invoke.MethodType;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class WebSecure {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.cors(cors -> cors.configurationSource(request -> corsConfiguration()))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(
                        exchanges ->
                                exchanges
                                        .pathMatchers("/api/image/*").permitAll()
                                        .anyExchange().authenticated());
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
