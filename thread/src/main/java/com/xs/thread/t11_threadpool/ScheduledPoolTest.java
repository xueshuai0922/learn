package com.xs.thread.t11_threadpool;


import java.util.Date;
import java.util.concurrent.*;

/**
 * @author xueshuai
 * @date 2021/3/9 21:30
 * @description
 */
public class ScheduledPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println(new Date());
            System.out.println("每2秒出现下,实现的原理是任务队列用了DelayedWorkQueue");
            System.out.println(new Date());
        },0,2000, TimeUnit.MILLISECONDS);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        ScheduledFuture<?> future = executorService.scheduleAtFixedRate(() -> {
            System.out.println("周期任务执行");
        }, 0, 1, TimeUnit.SECONDS);
        Object o = future.get();
        System.out.println(o+"");


    }
}
