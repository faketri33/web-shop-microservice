package org.example.usecase.basket;

import java.util.UUID;

import org.example.entity.BasketItem.model.BasketItem;
import org.example.entity.basket.gateway.BasketRepository;
import org.example.entity.basket.model.Basket;
import org.example.infrastructure.basket.dto.BasketDtoRequest;
import org.example.infrastructure.basket.gateway.BasketService;
import org.example.infrastructure.basketItem.gateway.BasketItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.ws.rs.NotFoundException;
import reactor.core.publisher.Mono;

@Service
public class BasketServiceImpl implements BasketService {

    private static final Logger log = LoggerFactory.getLogger(BasketServiceImpl.class);
    private final BasketRepository basketRepository;
    private final BasketItemService basketItemService;

    public BasketServiceImpl(BasketRepository basketRepository, BasketItemService basketItemService) {
        this.basketRepository = basketRepository;
        this.basketItemService = basketItemService;
    }

    @Override
    public Mono<Basket> findBasketById(UUID basketId) {
        log.info("Finding basket by id: {}", basketId);

        if (basketId == null) {
            log.warn("Basket ID is null");
            return Mono.empty();
        }

        return basketRepository.findById(basketId)
            .switchIfEmpty(Mono.defer(() -> {
                log.warn("Basket with id {} not found", basketId);
                return Mono.error(new NotFoundException("Basket not found for ID: " + basketId));
            }));
    }

    @Override
    public Mono<Basket> findBasketByUserId(UUID userId) {
        log.info("Finding basket by user id: {}", userId);
         
        if (userId == null) {
             log.warn("User ID is null");
             return Mono.empty();
        }

        return basketRepository.findByUserId(userId)
            .switchIfEmpty(Mono.defer(() -> {
                Basket newBasket = new Basket();
                newBasket.setUserId(userId);
                return save(newBasket);
            }));
    }

    @Override
    public Mono<Basket> save(Basket e) {
        return basketRepository.findByUserId(e.getUserId())
            .flatMap(existingBasket -> {
                log.info("Updating existing basket for user id: {}", e.getUserId());
                return basketRepository.save(existingBasket);
            })
            .switchIfEmpty(Mono.defer(() -> {
                log.info("Creating new basket for user id: {}", e.getUserId());
                return basketRepository.save(e);
            }))
            .doOnSuccess(savedBasket -> log.info("Basket saved successfully with id: {}", savedBasket.getId()));
    }

    @Override
    public Mono<Basket> save(BasketDtoRequest basketDtoRequest) {
        log.info("Saving basket from DTO request: {}", basketDtoRequest);
        
        if (basketDtoRequest == null) {
            log.warn("Basket DTO request is null");
            return Mono.error(new IllegalArgumentException("Basket DTO request cannot be null"));
        }

        Basket basket = new Basket();
        basket.setId(basketDtoRequest.getId());
        basket.setUserId(basketDtoRequest.getUserId());
        basket.setPrice(basketDtoRequest.getPrice());
        
        if (basket.getId() == null) {
            basket.setId(save(basket).block().getId());
        }

        if (basketDtoRequest.getProductId() != null) {
            basketDtoRequest.getProductId().forEach((productId, quantity) -> {
                basketItemService.save(new BasketItem(basket.getId(), productId, quantity, basketDtoRequest.getPrice()));
            });
        }

        return Mono.just(basket);

    }
}
