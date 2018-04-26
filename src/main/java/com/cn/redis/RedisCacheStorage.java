package com.cn.redis;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/7.
 */
public interface RedisCacheStorage<K, V> {
    boolean set(K key, V value);

    boolean hset(String cacheKey, K key, V value);

    V hget(String cacheKey, K key);

    Map<K, V> hget(String cacheKey);
}
