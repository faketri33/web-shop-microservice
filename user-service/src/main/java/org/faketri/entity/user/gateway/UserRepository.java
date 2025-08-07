package org.faketri.entity.user.gateway;


import org.faketri.entity.user.model.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;


public interface UserRepository extends ReactiveCrudRepository<Users, UUID> {

    Mono<Users> findById(UUID id);
    Mono<Users> findByUsername(String username);
}
