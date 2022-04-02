package com.xs.thread.t12_thread_complete_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author xueshuai
 * @date 2022/1/25 16:10
 * @description
 */
public class CompletableFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> AFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);//1s
                return 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        });
//        CompletableFuture<Void> BFuture = CompletableFuture.runAsync(() -> {
//            try {
//                Thread.sleep(10000);//1s
//                return;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        CompletableFuture<Void> BFuture = AFuture.thenAcceptAsync((a) -> {
            try {
                Thread.sleep(1000);//1s
                System.out.println(a+1);
                return ;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long start = System.currentTimeMillis();
        CompletableFuture.anyOf(AFuture).get();
        System.out.println("消耗时间(s)："+(System.currentTimeMillis()-start)/1000);
        System.out.println("异步回调");
    }
}
