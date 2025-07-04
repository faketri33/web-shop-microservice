package org.faketri.entity.user.gateway;


import org.faketri.entity.user.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {

    Mono<User> findById(UUID id);

    Mono<User> findByUsername(String username);
}
