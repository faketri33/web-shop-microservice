package org.faketri.entity.user.gateway;


import org.faketri.entity.user.model.Users;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;


public interface UserRepository extends ReactiveCrudRepository<Users, UUID> {
    @Query("INSERT INTO users (id, username, email, images, create_at) VALUES (:id, :username, :email, :images, :create_at) RETURNING *")
    Mono<Users> insert(Users users);
    Mono<Users> findById(UUID id);
    Mono<Users> findByUsername(String username);
}
