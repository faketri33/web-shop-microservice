package org.example.usecase.basketitem;

import java.util.UUID;

import org.example.entity.BasketItem.gateway.BasketItemRepository;
import org.example.entity.BasketItem.model.BasketItem;
import org.example.infrastructure.basketitem.gateway.BasketItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.ws.rs.NotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BasketItemServiceImpl implements BasketItemService {

    private static final Logger log = LoggerFactory.getLogger(BasketItemServiceImpl.class);
    private final BasketItemRepository basketItemRepository;

    public BasketItemServiceImpl(BasketItemRepository basketItemRepository) {
        this.basketItemRepository = basketItemRepository;
    }

    @Override
    public Flux<BasketItem> findItemsByBasketId(UUID basketId) {
        log.info("Finding items for basket id: {}", basketId);
        return basketItemRepository.findByBasketId(basketId);
    }

    @Override
    public Mono<BasketItem> findItemById(UUID itemId) {
        log.info("Finding item by id: {}", itemId);
        return basketItemRepository.findById(itemId)
            .switchIfEmpty(Mono.error(new NotFoundException("Basket item not found for ID: " + itemId)));
    }

    @Override
    public Mono<BasketItem> save(BasketItem item) {
        log.info("Saving basket item: {}", item);
        return basketItemRepository.save(item);
    }

    @Override
    public Mono<Void> deleteItem(UUID itemId) {
        log.info("Deleting basket item by id: {}", itemId);
        return basketItemRepository.deleteById(itemId);
    }

}
