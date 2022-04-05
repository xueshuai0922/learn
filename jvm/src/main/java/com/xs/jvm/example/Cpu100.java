package com.xs.jvm.example;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xueshuai
 * @date 2021/3/26 10:28
 * @description 错误使用线程池，导致频繁GC，因为线程数多了，走阻塞队列，但是这个阻塞队列是个int的最大值，就
 * 一直网阻塞队列中存放值，导致出现大对象
 * <p>
 * 解决办法：监听模式，不要进行死循环
 */
public class Cpu100 {

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws Exception {
        executor.setMaximumPoolSize(50);

        for (; ; ) {
            ScheduledFuture<?> scheduledFuture = executor.scheduleWithFixedDelay(() -> {
                while (true) {
                    long l = 100 * 100;
                }
            }, 2, 1, TimeUnit.SECONDS);
        }
    }
}


