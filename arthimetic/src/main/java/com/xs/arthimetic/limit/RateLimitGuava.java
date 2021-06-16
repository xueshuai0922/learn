package com.xs.arthimetic.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author xueshuai
 * @date 2021/5/14 8:33
 * @description 通过guava进行限流 算法
 */
public class RateLimitGuava {
    private  static  RateLimiter rateLimiter;



    public static void main(String[] args) {
        rateLimiter = RateLimiter.create(10);//QPS;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Random random = new Random();
        //模拟100线程进行同时并发
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    countDownLatch.await();
                    Thread.sleep(random.nextInt(500));
                    if (rateLimiter.tryAcquire()) {
                        System.out.println("顺利通过");
                    }else {
                        System.out.println("不好意思被限流了");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
        countDownLatch.countDown();

    }



}
