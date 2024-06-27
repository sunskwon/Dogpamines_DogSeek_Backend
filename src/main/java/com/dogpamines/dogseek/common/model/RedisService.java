package com.dogpamines.dogseek.common.model;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void countVisits(String key) {

        ValueOperations<String, String> countVisit = redisTemplate.opsForValue();

        int count = Integer.parseInt(countVisit.get(key));

        if (count > 0) {

            String updatedCount = String.valueOf(count + 1);

            countVisit.set(key, updatedCount);
        } else {

            String firstVisit = String.valueOf(1);

            countVisit.set(key, firstVisit);
        }
    }

    public int getCount(String key) {

        ValueOperations<String, String> countVisit = redisTemplate.opsForValue();

        int count = Integer.parseInt(countVisit.get(key));

        return count;
    }
}