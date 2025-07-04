package org.product.usecase.messaging;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.product.infastructure.messaging.gateway.KafkaProducers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducersImpl implements KafkaProducers {
    private final String topic;
    private final KafkaTemplate<String, String> kafkaProducer;

    public KafkaProducersImpl(@Value("${TOPIC_NAME}") String topic, KafkaTemplate<String, String> kafkaProducer) {
        this.topic = topic;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void sendMessage(String message) {
        kafkaProducer.send(new ProducerRecord<>(topic, message));
    }
}
