package com.example.cartService.service.serviceImpl;

import com.example.cartService.service.BaseRedisService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class BaseRedisServiceImpl implements BaseRedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final HashOperations<String , String, Object> hashOperations;

    public BaseRedisServiceImpl(RedisTemplate<String, Object> redisTemplate, HashOperations<String , String, Object> hashOperations) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = hashOperations;
    }

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setTimeToLive(String key, long timeOutInDays) {
        redisTemplate.expire(key, timeOutInDays, TimeUnit.DAYS);
    }

    @Override
    public void hashSet(String key, String field, Object value) {
        hashOperations.put(key,field, value);
    }

    @Override
    public boolean hashExits(String key, String field) {
        return hashOperations.hasKey(key, field);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Object hashGet(String key, String field) {
        return hashOperations.get(key, field);
    }

    @Override
    public List<Object> hashGetByFieldPrefix(String key, String fieldPrefix) {
        List<Object> objects = new ArrayList<>();
        Map<String, Object> entries  = hashOperations.entries(key);

        for (Map.Entry<String , Object> entry :entries.entrySet()) {
            if(entry.getKey().startsWith(fieldPrefix)) {
                objects.add(entry.getValue());
            }
        }
        return objects;
    }

    @Override
    public Set<String> getFieldPrefix(String key) {
        return hashOperations.entries(key).keySet();
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void deleteFieldInHash(String key, String field) {
        hashOperations.delete(key, field);
    }

    @Override
    public void deleteManyFieldInHash(String key, List<String> fields) {
        for (String s : fields) {
            hashOperations.delete(key, s);
        }
    }
}
