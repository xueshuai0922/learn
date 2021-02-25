package com.xs.shiro.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

/**
 * @author xueshuai
 * @date 2021/1/14 12:15
 * @description
 */

public class RedisCache<K, V> implements Cache<K, V> {


    private RedisTemplate redisTemplate;

    public RedisCache(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisCache() {
        System.out.println("注入rediscache");
        System.out.println("redisTemplate:" + redisTemplate);
    }

    @Override
    public V get(K key) throws CacheException {
        if (key != null) {
            try {
                return (V) redisTemplate.opsForValue().get(key);
            } catch (Exception e) {

                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        System.out.println("rediscache put key:{" + key + "},value:{" + value + "}");
        redisTemplate.opsForValue().set(key, value);
        return null;
    }

    @Override
    public V remove(K key) throws CacheException {
        System.out.println("rediscache delete key:{" + key + "}");
        redisTemplate.delete(key);
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
