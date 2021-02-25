package com.xs.shiro.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author xueshuai
 * @date 2021/1/14 9:44
 * @description redis配置
 */
@Configuration
public class RedisConfig {
    /**
     * shiro-redis 使用，将key进行序列化，value默认为jdk序列化
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate shiroRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //key 和hash key设置String序列化
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        //进行属性配置
        redisTemplate.afterPropertiesSet();
        return redisTemplate;


    }

    /**
     * redis正常配置使用的模板类 进行序列化
     * redisTemplate
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        System.out.println("redis");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);


        //key 和hash key设置String序列化
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        //value 、hash value设置json序列化
        //TODO 使用GenericJackson2JsonRedisSerializer Jackson2JsonRedisSerializer反序列化有问题
        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        redisTemplate.setValueSerializer(objectJackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(objectJackson2JsonRedisSerializer);
        //进行属性配置
        redisTemplate.afterPropertiesSet();
        return redisTemplate;


    }
}
