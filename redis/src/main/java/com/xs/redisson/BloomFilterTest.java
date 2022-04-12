package com.xs.redisson;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

/**
 * @author xueshuai
 * @date 2021/12/18 15:25
 * @description 布隆过滤器测试
 *              布隆过滤器的作用：  判断数据是否存在，可以有效防止缓存穿透
 *                  由于存在误判率，所以当布隆过滤出存在，也有一定概率是不存在的
 *                  如果不存在，那肯定是不存在
 *
 */
public class BloomFilterTest {


    public static void main(String[] args) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setPassword("winning.2019");
        singleServerConfig.setAddress("redis://172.17.1.150:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloomFilter");
        //初始化一个长度为10000的数组，误判率为1%
        bloomFilter.tryInit(1000000,0.01);

        bloomFilter.add(1);
        //判断是否存在
        boolean contains = bloomFilter.contains(1);
        System.out.println("是否存在1？ "+contains);
        boolean contains1 = bloomFilter.contains(22);
        System.out.println("是否存在22？ "+contains1);



    }
}
