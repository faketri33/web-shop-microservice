package org.faketri.entity.user.gateway;


import org.faketri.entity.user.model.UserLikedProduct;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface UserLikedProductRepository extends ReactiveCrudRepository<UserLikedProduct, UUID> {
    Flux<UserLikedProduct> findByUserId(UUID userId);
    Mono<UserLikedProduct> findByUserIdAndProductId(UUID userId, UUID productId);

}
