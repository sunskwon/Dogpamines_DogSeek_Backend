package com.dogpamines.dogseek.auth.model.service;

import com.dogpamines.dogseek.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
        redisTemplate.opsForValue().set(userCode, refreshToken, duration, TimeUnit.MILLISECONDS);
    }

    public String getRefreshToken(String userCode) {
        return redisTemplate.opsForValue().get(userCode);
    }

    public void deleteRefreshToken(String userCode) {
        redisTemplate.delete(userCode);
    }
}
