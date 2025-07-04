package org.product.infastructure.web.event;


import org.product.entity.product.events.ProductPublishedEvent;
import org.product.infastructure.messaging.gateway.KafkaProducers;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListener {

    private final KafkaProducers producer;

    public ProductEventListener(KafkaProducers producer) {
        this.producer = producer;
    }

    @EventListener(ProductPublishedEvent.class)
    public void reportProductPublished(ProductPublishedEvent event){
        producer.sendMessage(event.getMessage());
    }
}
