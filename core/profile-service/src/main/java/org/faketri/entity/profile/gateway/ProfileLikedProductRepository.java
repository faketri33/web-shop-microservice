package org.faketri.entity.profile.gateway;


import org.faketri.entity.profile.model.ProfileLikedProduct;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProfileLikedProductRepository extends ReactiveCrudRepository<ProfileLikedProduct, UUID> {

    @Query("SELECT p FROM product p LEFT JOIN user_favorite_product ufp on ufp.product_id = p.id where ufp.user_id = :1")
    Flux<ProfileLikedProduct> findByProfileId(UUID userId);

    Mono<ProfileLikedProduct> findByProfileIdAndProductId(UUID userId, UUID productId);

    void deleteByProfileIdAndProductId(UUID userID, ProfileLikedProduct u);
}
