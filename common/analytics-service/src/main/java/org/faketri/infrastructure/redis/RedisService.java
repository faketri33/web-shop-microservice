package org.faketri.infrastructure.redis;

import org.springframework.data.domain.Range;
import org.springframework.data.redis.core.ReactiveRedisTemplate;

public class RedisService {
    private final ReactiveRedisTemplate<String, String> redisService;

    public RedisService(ReactiveRedisTemplate<String, String> redisService) {
        this.redisService = redisService;
    }

    public void incrementScore(String productId) {
        redisService.opsForZSet().incrementScore("product:view:ranking", productId, 1);
    }

    public void clearOldViews(int limit) {
        redisService.opsForZSet().removeRange("product:view:ranking",
                Range.from(Range.Bound.inclusive(0L)).to(Range.Bound.inclusive(-limit - 1L)));
    }
}
