package org.example.infrastructure.basketItem.controller;

import java.util.UUID;

import org.example.entity.BasketItem.model.BasketItem;
import org.example.entity.basket.model.Basket;
import org.example.infrastructure.basket.dto.BasketDtoRequest;
import org.example.infrastructure.basket.gateway.BasketService;
import org.example.infrastructure.basketItem.gateway.BasketItemService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private final BasketService basketService;
    private final BasketItemService basketItemService;

    public BasketController(BasketService basketService, BasketItemService basketItemService) {
        this.basketService = basketService;
        this.basketItemService = basketItemService;
    }

    @RequestMapping("/{basketId}")
    public Mono<Basket> getBasketById(@PathVariable UUID basketId) {
        return basketService.findBasketById(basketId);
    }

    @RequestMapping("/user/{userId}")
    public Mono<Basket> getBasketByUserId(@PathVariable UUID userId) {
        return basketService.findBasketByUserId(userId);
    }
    
    @RequestMapping("/{basketId}/items")
    public Flux<BasketItem> getItemsByBasketId(UUID basketId) {
        return basketItemService.findItemsByBasketId(basketId);
    }

    @RequestMapping(path = "/items/save", method = RequestMethod.POST)
    public Mono<Basket> save(@RequestBody BasketDtoRequest basketDtoRequest) {
        return basketService.save(basketDtoRequest);
    }
}
