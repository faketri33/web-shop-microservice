package org.faketri.infrastructure.user.service;

import org.faketri.entity.user.model.UserLikedProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.UUID;

public interface UserLikeProductService {
    Flux<UserLikedProduct> findById(UUID uuid);
    Mono<UserLikedProduct> findByUserIdAndProductId(UUID userId, UUID productId);
    Mono<UserLikedProduct> addToFavorites(UUID userId, UUID productId);
    Mono<UserLikedProduct> save(UserLikedProduct u);
    void remove(UserLikedProduct u);
}
