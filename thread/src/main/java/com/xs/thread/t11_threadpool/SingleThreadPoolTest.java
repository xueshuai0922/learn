package com.xs.thread.t11_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xueshuai
 * @date 2021/3/23 11:33
 * @description
 */
public class SingleThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->{
            System.out.println("this is newSingleThreadExecutor");
        });
    }
}
