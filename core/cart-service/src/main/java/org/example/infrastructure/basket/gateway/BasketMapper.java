package org.example.infrastructure.basket.gateway;

import org.example.entity.BasketItem.model.BasketItem;
import org.example.entity.basket.model.Basket;
import org.example.infrastructure.basket.dto.BasketDto;
import org.example.infrastructure.basketitem.gateway.BasketItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class BasketMapper {
    private static final Logger log = LoggerFactory.getLogger(BasketMapper.class);
    private final BasketItemService basketItemService;

    public BasketMapper(BasketItemService basketItemService) {
        this.basketItemService = basketItemService;
    }

    public Mono<BasketDto> toDto(Basket basket) {
        log.debug("toDto: basket={}", basket);
        return basketItemService.findItemsByBasketId(basket.getId())
                .collectMap(BasketItem::getProductId, BasketItem::getQuantity)
                .map(products -> new BasketDto(
                        basket.getId(),
                        basket.getUserId(),
                        products
                ));
    }
}
