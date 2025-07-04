package org.faketri.infastructure.user.service;

import org.faketri.entity.user.model.User;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {

    Mono<User> findById(UUID id);
    Mono<User> findByUsername(String username);
    Mono<User> save(User user);
}
