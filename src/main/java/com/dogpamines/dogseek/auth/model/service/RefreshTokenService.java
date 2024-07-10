package com.dogpamines.dogseek.auth.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RefreshTokenService {

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public RefreshTokenService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveRefreshToken(String userCode, String refreshToken, long duration) {

        try {
            redisTemplate.opsForValue().set(userCode, refreshToken, duration, TimeUnit.MILLISECONDS);

        } catch (RedisConnectionFailureException e) {

            System.out.println("redis와 연결되지 않음");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public String getRefreshToken(String userCode) {

        String refreshToken = "";

        try {

            refreshToken = redisTemplate.opsForValue().get(userCode);

        } catch (RedisConnectionFailureException e) {

            System.out.println("redis와 연결되지 않음");

        } catch (Exception e) {

            e.printStackTrace();

        }

        return refreshToken;
    }

    public void deleteRefreshToken(String userCode) {

        try {

            redisTemplate.delete(userCode);

        } catch (RedisConnectionFailureException e) {

            System.out.println("redis와 연결되지 않음");

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
