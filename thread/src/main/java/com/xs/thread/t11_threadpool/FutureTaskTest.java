package com.xs.thread.t11_threadpool;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author xueshuai
 * @date 2021/4/12 13:58
 * @description
 */
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<?> submit = executorService.submit(() ->
        {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("call ...");
            return 123;
        });
        Object o = submit.get();
        System.out.println(o+"   结束时间"+new Date());
        System.out.println("main线程 结束的时间"+new Date());


        //不进行关闭，future的get就会一直阻塞
        executorService.shutdown();

    }
}
