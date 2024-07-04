package com.dogpamines.dogseek.common.model.service;

import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addViewCount(String key) {

        try {

            ValueOperations<String, String> countView = redisTemplate.opsForValue();

            Optional<String> tempVisit = Optional.ofNullable(countView.get(key));

            if (!tempVisit.isEmpty()) {

                int count = Integer.parseInt(countView.get(key));

                String updatedCount = String.valueOf(count + 1);

                countView.set(key, updatedCount);
            } else {

                String firstVisit = String.valueOf(1);

                countView.set(key, firstVisit);
            }
        } catch (RedisConnectionFailureException e) {
            System.out.println("redis와 연결되지 않음");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}