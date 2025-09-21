package org.example.infrastructure.basketitem.controller;

import java.util.UUID;

import org.example.entity.BasketItem.model.BasketItem;
import org.example.infrastructure.basket.dto.BasketDto;
import org.example.infrastructure.basket.gateway.BasketService;
import org.example.infrastructure.basketitem.gateway.BasketItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/{basketId}")
    public Mono<BasketDto> getBasketById(@PathVariable UUID basketId) {
        return basketService.findBasketById(basketId);
    }

    @GetMapping("/user/{userId}")
    public Mono<BasketDto> getBasketByUserId(@PathVariable UUID userId) {
        return basketService.findBasketByUserId(userId);
    }
    
    @GetMapping("/{basketId}/items")
    public Flux<BasketItem> getItemsByBasketId(@PathVariable UUID basketId) {
        return basketItemService.findItemsByBasketId(basketId);
    }

    @PostMapping(path = "/items/save")
    public Mono<BasketDto> save(@RequestBody BasketDto basketDtoRequest) {
        return basketService.save(basketDtoRequest);
    }
}
