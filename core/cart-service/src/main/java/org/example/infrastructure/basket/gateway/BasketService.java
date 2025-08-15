package org.example.infrastructure.basket.gateway;

import java.util.UUID;

import org.example.entity.basket.model.Basket;
import org.example.infrastructure.basket.dto.BasketDtoRequest;

import reactor.core.publisher.Mono;

public interface BasketService {
    
    Mono<Basket> findBasketById(UUID basketId);
    Mono<Basket> findBasketByUserId(UUID userId);
    Mono<Basket> save(BasketDtoRequest basketDtoRequest);
    Mono<Basket> save(Basket e);
}
