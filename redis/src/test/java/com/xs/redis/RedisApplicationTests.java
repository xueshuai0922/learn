package com.xs.redis;

import com.xs.redis.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RedisApplicationTests {

    @Test
    void contextLoads() {
    }


    @Resource
    private RedisUtil redisUtil;
    @Test
    void set(){
        redisUtil.set("x","123");
        System.out.println(redisUtil.get("x"));
    }

}
