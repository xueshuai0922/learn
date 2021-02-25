package com.xs.shiro.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @author xueshuai
 * @date 2021/1/14 10:50
 * @description
 */
public class RedisCacheManager implements CacheManager {

    private RedisCache redisCache;

    public RedisCacheManager(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return redisCache;
    }
}
