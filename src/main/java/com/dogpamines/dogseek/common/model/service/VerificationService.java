package com.dogpamines.dogseek.common.model.service;

import io.lettuce.core.RedisException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationService {

    private static final long EXPIRATION_TIME = 5; // in minutes

    private final StringRedisTemplate redisTemplate;

    public VerificationService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void createVerificationToken(String email, String token) {
        try {
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            ops.set(email, token, EXPIRATION_TIME, TimeUnit.MINUTES);
        } catch (RedisConnectionFailureException | RedisException e) {
            System.out.println("redis와 연결되지 않음");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getVerificationToken(String email) {

        try {
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            return ops.get(email);
        } catch (RedisConnectionFailureException | RedisException e) {
            System.out.println("redis와 연결되지 않음");
            return "fail";
        }
    }

    public boolean validateVerificationToken(String email, String token) {
        String storedToken = getVerificationToken(email);
        return token.equals(storedToken);
    }
}


