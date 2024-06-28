package com.dogpamines.dogseek.common.model.service;

import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void countVisits(String key) {

        try {

            ValueOperations<String, String> countVisit = redisTemplate.opsForValue();

            Optional<String> tempVisit = Optional.ofNullable(countVisit.get(key));

            if (!tempVisit.isEmpty()) {

                int count = Integer.parseInt(countVisit.get(key));

                String updatedCount = String.valueOf(count + 1);

                countVisit.set(key, updatedCount);
            } else {

                String firstVisit = String.valueOf(1);

                countVisit.set(key, firstVisit);
            }
        } catch (RedisConnectionFailureException e) {
            System.out.println("redis와 연결되지 않음");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}