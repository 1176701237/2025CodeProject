package com.example.demo.stringRedisTemplate.impl;

import com.example.demo.stringRedisTemplate.service.myRedisTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class myRedisTemplateServiceImpl implements myRedisTemplateService {

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public boolean set(String key, String value, long timeout, TimeUnit unit) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public String getAndSet(String key, String value) {
        return stringRedisTemplate.opsForValue().getAndSet(key, value);
    }

    @Override
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Double increment(String key, double delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public void multiSet(Map<String, String> map) {
        stringRedisTemplate.opsForValue().multiSet(map);
    }

    @Override
    public boolean multiSetIfAbsent(Map<String, String> map) {
        return stringRedisTemplate.opsForValue().multiSetIfAbsent(map);
    }

    @Override
    public List<String> multiGet(Collection<String> keys) {
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public String getRange(String key, long start, long end) {
        return stringRedisTemplate.opsForValue().get(key, start, end);
    }

    @Override
    public Integer append(String key, String value) {
        return stringRedisTemplate.opsForValue().append(key, value);
    }

    @Override
    public void setBit(String key, long offset, boolean value) {
        stringRedisTemplate.opsForValue().setBit(key, offset, value);
    }

    @Override
    public boolean getBit(String key, long offset) {
        return stringRedisTemplate.opsForValue().getBit(key, offset);
    }




    @Override
    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    @Override
    public boolean expire(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.expire(key, timeout, unit);
    }

    @Override
    public Long getExpire(String key, TimeUnit timeUnit) {
        return stringRedisTemplate.getExpire(key, timeUnit);
    }

    @Override
    public void rename(String oldKey, String newKey) {
        stringRedisTemplate.rename(oldKey, newKey);
    }

    @Override
    public boolean move(String key, int dbIndex) {
        return stringRedisTemplate.move(key, dbIndex);
    }

    @Override
    public String type(String key) {
        return stringRedisTemplate.type(key).name();
    }

    @Override
    public Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }
}