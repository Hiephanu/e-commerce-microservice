package com.example.cartService.service;

import java.util.List;
import java.util.Set;

public interface BaseRedisService {
    void set(String key, String value);
    void setTimeToLive(String key, long timeOutInDays);
    void hashSet(String key, String field,Object value);
    boolean hashExits(String key, String field);
    Object get(String key);
    Object hashGet(String key, String value);
    List<Object> hashGetByFieldPrefix(String key, String fieldPrefix);
    Set<String> getFieldPrefix(String  key);
    void delete(String key);
    void deleteFieldInHash(String key, String field);
    void deleteManyFieldInHash(String key, List<String> fields);
}
