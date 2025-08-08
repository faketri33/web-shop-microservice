package org.example.entity.BasketItem.gateway;

import java.util.UUID;

import org.example.entity.BasketItem.model.BasketItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface BasketItemRepository extends ReactiveCrudRepository<BasketItem, UUID> {
    Flux<BasketItem> findByBasketId(UUID basketId);
}
