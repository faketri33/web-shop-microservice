package org.product.infrastructure.web.messaging.gateway;

public interface KafkaProducers {
    void sendMessage(String topic, String message);
}
