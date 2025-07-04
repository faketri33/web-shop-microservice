package org.faketri.infastructure.user.service;


import org.faketri.entity.user.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService extends ReactiveCrudRepository<User, UUID> {

    Mono<User> findById(UUID id);

    Mono<User> findByUsername(String username);


}
