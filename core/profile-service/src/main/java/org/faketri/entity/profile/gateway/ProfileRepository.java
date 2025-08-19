package org.faketri.entity.profile.gateway;


import org.faketri.entity.profile.model.Profile;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;


public interface ProfileRepository extends ReactiveCrudRepository<Profile, UUID> {
    @Query("INSERT INTO profile (id, username, email, images, create_at) VALUES (:id, :username, :email, :images, :create_at) RETURNING *")
    Mono<Profile> insert(Profile profile);

    Mono<Profile> findById(UUID id);

    Mono<Profile> findByUsername(String username);
}
