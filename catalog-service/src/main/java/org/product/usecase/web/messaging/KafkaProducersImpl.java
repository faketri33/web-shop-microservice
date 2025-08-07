package org.product.usecase.web.messaging;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.product.infrastructure.web.messaging.gateway.KafkaProducers;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducersImpl implements KafkaProducers {

    private final KafkaTemplate<String, String> kafkaProducer;

    public KafkaProducersImpl(KafkaTemplate<String, String> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void sendMessage(String topic, String message) {
        kafkaProducer.send(new ProducerRecord<>(topic, message));
    }
}
