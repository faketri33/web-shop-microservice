package org.faketri.infastructure.secure.config;

import io.netty.util.internal.StringUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.logging.Logger;


@Component
public class JwtAuthFilterChains implements WebFilter {

    private static final Logger log = Logger.getLogger(JwtAuthFilterChains.class.getName());
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtUtils jwtUtils;

    public JwtAuthFilterChains(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = getTokenFromRequest(exchange);

        if(StringUtil.isNullOrEmpty(token) || !token.startsWith(BEARER_PREFIX))
            return chain.filter(exchange);

        token = token.substring(7);

        try {
            if(!jwtUtils.validToken(token))
                throw new RuntimeException("Not valid token");

            String username = jwtUtils.extractUserName(token);

            if(StringUtil.isNullOrEmpty(username))
                return chain.filter(exchange);

            SecurityContext context = SecurityContextHolder.createEmptyContext();

            UserDetails userDetails = new User(username, "", Collections.emptyList());
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(new SecurityContextImpl(auth))));


        } catch (Exception ex) {
            log.warning(ex.getLocalizedMessage());
        }

        return chain.filter(exchange);
    }

    private String getTokenFromRequest(ServerWebExchange exchange){
        return exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    }
}
