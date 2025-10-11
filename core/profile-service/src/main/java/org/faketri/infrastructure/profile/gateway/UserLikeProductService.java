package org.faketri.infrastructure.profile.gateway;

import org.faketri.entity.profile.model.ProfileLikedProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserLikeProductService {
    Flux<ProfileLikedProduct> findById(UUID uuid);

    Mono<ProfileLikedProduct> addToFavorites(UUID userId, UUID productId);

    Mono<ProfileLikedProduct> save(ProfileLikedProduct u);

    void remove(UUID userID, ProfileLikedProduct u);
}
