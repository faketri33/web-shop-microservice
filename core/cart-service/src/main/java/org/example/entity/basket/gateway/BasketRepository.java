package org.example.entity.basket.gateway;

import org.example.entity.basket.model.Basket;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BasketRepository extends ReactiveCrudRepository<Basket, UUID> {

    Mono<Basket> findByUserId(UUID userId);
}
