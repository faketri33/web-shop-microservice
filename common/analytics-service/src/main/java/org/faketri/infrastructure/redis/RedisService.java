package org.faketri.infrastructure.redis;

import org.springframework.data.domain.Range;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private final ReactiveRedisTemplate<String, String> redisTemplate;

    public RedisService(ReactiveRedisTemplate<String, String> redisService) {
        this.redisTemplate = redisService;
    }

    public void incrementScore(String productId) {
        redisTemplate.opsForZSet()
                .incrementScore("product:view:ranking", productId, 1)
                .subscribe();
    }

    public void clearOldViews(int limit) {
        redisTemplate.opsForZSet()
                .removeRange("product:view:ranking",
                Range.from(Range.Bound.inclusive(0L))
                        .to(Range.Bound.inclusive(-limit - 1L)))
                .subscribe();
    }
}
