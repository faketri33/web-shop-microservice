package org.catalog.infrastructure.web.event;


import org.catalog.entity.product.events.ProductPublishedEvent;
import org.catalog.entity.product.events.ProductViewEvent;
import org.catalog.infrastructure.web.messaging.gateway.KafkaProducers;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListener {

    private final KafkaProducers producer;

    public ProductEventListener(KafkaProducers producer) {
        this.producer = producer;
    }

    @EventListener(ProductPublishedEvent.class)
    public void reportProductPublished(ProductPublishedEvent event) {
        producer.sendMessage(event.getToken(), event.getMessage());
    }

    @EventListener(ProductViewEvent.class)
    public void reportProductViewEvent(ProductViewEvent event) {
        producer.sendMessage(event.getToken(), event.getProductId());
    }
}
