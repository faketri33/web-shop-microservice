package org.faketri.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private final String topic = "${TOPIC_NAME}";
    private final String group = "${KAFKA_GROUP_ID}";
    private final ReactiveRedisTemplate<String, String> redisService;

    public KafkaConsumer(ReactiveRedisTemplate<String, String> redisService) {
        this.redisService = redisService;
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic(topic, 1, (short) 1);
    }

    @KafkaListener(topics = topic, groupId = group)
    public void listen(ConsumerRecord<String, String> record){
        System.out.println(record.value());
        redisService.opsForZSet().incrementScore("product:view:ranking", record.value(), 1);
    }

    public void clearOldViews(int limit) {
        redisService.opsForZSet().removeRange("product:view:ranking",
                Range.from(Range.Bound.inclusive(0L)).to(Range.Bound.inclusive(-limit -1L)));
    }
}
