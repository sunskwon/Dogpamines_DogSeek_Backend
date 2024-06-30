package com.dogpamines.dogseek.common.model.service;

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
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(email, token, EXPIRATION_TIME, TimeUnit.MINUTES);
    }

    public String getVerificationToken(String email) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return ops.get(email);
    }

    public boolean validateVerificationToken(String email, String token) {
        String storedToken = getVerificationToken(email);
        return token.equals(storedToken);
    }
}


