package org.example.infrastructure.basket.gateway;

import java.util.UUID;

import org.example.entity.basket.model.Basket;
import org.example.infrastructure.basket.dto.BasketDto;

import reactor.core.publisher.Mono;

public interface BasketService {
    
    Mono<BasketDto> findBasketById(UUID basketId);
    Mono<BasketDto> findBasketByUserId(UUID userId);
    Mono<BasketDto> save(BasketDto basketDtoRequest);
    Mono<BasketDto> save(Basket e);
}
