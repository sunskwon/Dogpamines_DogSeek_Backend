package com.dogpamines.dogseek.redistTest;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
public class CacheService {

    private final RedisTemplate<String, String> redisTemplate;
    private final String VISITANT_KEY = "visitant";

    public CacheService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void visitMember(String memberId) {
        System.out.println("visitMember...");
        SetOperations<String, String> visitantLog = redisTemplate.opsForSet();
        visitantLog.add(VISITANT_KEY, memberId);
        ValueOperations<String, String> visitTime = redisTemplate.opsForValue();
        if(visitTime.get(memberId) != null && visitTime.get(memberId) != "") {
            int visits = Integer.parseInt(visitTime.get(memberId));
            System.out.println("visits = " + visits);
            visitTime.set(memberId, String.valueOf(visits + 1));
        } else {
            visitTime.set(memberId, String.valueOf(1));
            System.out.println("fisrt visit");
        }
    }

    public Optional<Long> getMemberVisitedCount() {
        SetOperations<String, String> visitantLog = redisTemplate.opsForSet();
        return Optional.ofNullable(visitantLog.size(VISITANT_KEY));
    }

    public void clearVisitant() {
        redisTemplate.delete(VISITANT_KEY);
    }
}
