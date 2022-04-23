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

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://172.17.1.150:6379")
                .setPassword("winning.2019");
        RedissonClient redissonClient = Redisson.create(config);
        RLock lockname = redissonClient.getLock("lockname");
        //加锁
//        lockname.tryLock(2,2,TimeUnit.MILLISECONDS);
        lockname.lock();
        System.out.println("是否锁定"+lockname.isLocked());


        //todo 测试watchdog  watchdog的作用：在业务逻辑超过过期时间的时候，监控业务逻辑如果没有结束（即仍旧有锁),延长锁的过期时间
        // watchDog 只有在未显示指定加锁时间时才会生效,即不给leaseTime值



        Thread.sleep(60000);//模拟加锁后的业务逻辑过程  默认的watchdog的过期时间为30000ms


        //释放锁
        lockname.unlock();
    }

}
