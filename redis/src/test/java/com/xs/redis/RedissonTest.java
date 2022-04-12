package com.xs.redis;

import com.xs.redis.util.RedisUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.concurrent.ForkJoinPool;

/**
 * @author xueshuai
 * @date 2022/4/7 9:48
 * @description Redisson测试
 */
@SpringBootTest
public class RedissonTest  extends RedisApplicationTests{

    private RedissonClient redissonClient;

    @Autowired
    private RedisUtil redisUtils;

    @Before
    public void initRedisson(){
        String ip="redis://172.17.1.150:6379";
        String pass="winning.2019";
        Config config = new Config();
        config.useSingleServer()
                .setAddress(ip)
                .setPassword(pass);

        redissonClient = Redisson.create(config);

    }
    @After
    public void closeRedisson(){
        redissonClient.shutdown();
    }

    /**
     * 利用bitmap插入数据量巨大的数据，因为bitmap，8个bit
     */
    @Test
    public void bitMap(){

            ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
            forkJoinPool.submit(()->{
                for (int i = 0; i < 1000_0000; i++) {
                redisUtils.setBit("bit",i,true);
                System.out.println(i);
                }
            });



    }

    /**
     * 结合上边的大数据量，从大数据量中获取某个值是否存在
     */
    @Test
    public void getBitMap(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Boolean bit = redisUtils.getBit("bit", 6689);
        System.out.println(bit);
        stopWatch.stop();
        double totalTime = stopWatch.getTotalTimeMillis();
        System.out.println(totalTime);
    }






}
