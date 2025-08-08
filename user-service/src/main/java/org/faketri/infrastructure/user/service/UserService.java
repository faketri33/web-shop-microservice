package org.faketri.infrastructure.user.service;

import org.faketri.entity.user.model.Users;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {

    Mono<Users> findById(UUID id);

    Mono<Users> findByUsername(String username);
    Mono<Users> findMe(Users user);
    Mono<Users> findMe(JwtAuthenticationToken token);
    Mono<Users> save(Users user);
    Mono<Users> insert(Users user);
    Users extractFromToken(JwtAuthenticationToken token);
}
