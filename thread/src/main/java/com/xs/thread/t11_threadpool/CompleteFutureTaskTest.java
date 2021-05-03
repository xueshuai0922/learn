package com.xs.thread.t11_threadpool;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author xueshuai
 * @date 2021/4/12 14:49
 * @description
 */
public class CompleteFutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(User::getAge)
                .thenApply((age) -> {
                    System.out.println(age);
                    System.out.println(Thread.currentThread().getName()+" 结束时间 :"+new Date());
                    return null;
                })
                .exceptionally(e -> {
                    e.getMessage();
                    return null;
                }).get();

        System.out.println("main线程结束时间 ："+new Date());
    }

    static  class User{
        public  static int getAge(){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 18;
        }
    }
}
