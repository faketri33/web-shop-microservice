package org.faketri.infrastructure.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.faketri.infrastructure.redis.RedisService;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private final String topic = "${TOPIC_NAME}";
    private final String group = "${KAFKA_GROUP_ID}";
    private final RedisService redisService;

    public KafkaConsumer(RedisService redisService) {
        this.redisService = redisService;
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic(topic, 1, (short) 1);
    }

    @KafkaListener(topics = topic, groupId = group)
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println(record.value());
        redisService.incrementScore(record.value());
    }
}
