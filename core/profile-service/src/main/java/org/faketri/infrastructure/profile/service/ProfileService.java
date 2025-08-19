package org.faketri.infrastructure.profile.service;

import org.faketri.entity.profile.model.Profile;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProfileService {

    Mono<Profile> findById(UUID id);

    Mono<Profile> findByUsername(String username);

    Mono<Profile> findMe(Profile user);

    Mono<Profile> findMe(JwtAuthenticationToken token);

    Mono<Profile> save(Profile user);

    Mono<Profile> insert(Profile user);

    Profile extractFromToken(JwtAuthenticationToken token);
}
