package org.product.infastructure.messaging.gateway;

public interface KafkaProducers {
    void sendMessage(String topic, String message);
}
