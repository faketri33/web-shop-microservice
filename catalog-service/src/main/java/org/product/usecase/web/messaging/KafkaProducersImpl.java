package org.product.usecase.web.messaging;


import org.apache.kafka.clients.producer.ProducerRecord;
import org.product.infrastructure.web.messaging.gateway.KafkaProducers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducersImpl implements KafkaProducers {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducersImpl.class);

    private final KafkaTemplate<String, String> kafkaProducer;

    public KafkaProducersImpl(KafkaTemplate<String, String> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void sendMessage(String topic, String message) {
        log.info("Sending message to topic {}: {}", topic, message);
        kafkaProducer.send(new ProducerRecord<>(topic, message));
    }
}
