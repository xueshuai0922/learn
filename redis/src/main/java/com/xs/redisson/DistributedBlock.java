package com.xs.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author xueshuai
 * @date 2022/1/15 18:38
 * @description redis实现 分布式锁
 */
public class DistributedBlock {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://172.17.1.150:6379")
                .setPassword("827681776");
        RedissonClient redissonClient = Redisson.create();
        RLock lockname = redissonClient.getLock("lockname");
    }

}
