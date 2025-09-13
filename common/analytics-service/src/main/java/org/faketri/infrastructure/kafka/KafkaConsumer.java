package org.faketri.infrastructure.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.faketri.infrastructure.redis.RedisService;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final String TOPIC = "${TOPIC_NAME}";
    private static final String GROUP = "${KAFKA_GROUP_ID}";
    private final RedisService redisService;

    public KafkaConsumer(RedisService redisService) {
        this.redisService = redisService;
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic(TOPIC, 1, (short) 1);
    }

    @KafkaListener(topics = TOPIC, groupId = GROUP)
    public void listen(ConsumerRecord<String, String> product) {
        redisService.incrementScore(product.value());
    }
}
