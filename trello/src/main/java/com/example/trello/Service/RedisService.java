package com.example.trello.Service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RedisService {

    final Integer DURATION_TIME = 5;

    @Autowired
    final RedisTemplate template;

    public void saveValueToRedis(String key, Object value) {
        template.opsForValue().set(key, value, DURATION_TIME, TimeUnit.MINUTES);
    }

    public Object getValueFromRedis(String key) {
        return template.opsForValue().get(key);
    }

    public void deleteValue(String key) {
        template.delete(key);
    }
}
