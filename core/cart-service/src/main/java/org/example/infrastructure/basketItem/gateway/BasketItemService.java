package org.example.infrastructure.basketItem.gateway;

import java.util.UUID;

import org.example.entity.BasketItem.model.BasketItem;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BasketItemService {
    Flux<BasketItem> findItemsByBasketId(UUID basketId);
    Mono<BasketItem> findItemById(UUID itemId);
    Mono<BasketItem> save(BasketItem item);
    Mono<Void> deleteItem(UUID itemId);
}
