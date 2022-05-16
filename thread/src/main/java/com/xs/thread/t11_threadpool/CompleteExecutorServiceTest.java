package com.xs.thread.t11_threadpool;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author xueshuai
 * @date 2022/4/28 9:10
 * @description
 * 　JDK 8的CompletionService相对于之前版本的Future而言，
 * 其优势是能够尽可能快的得到执行完成的任务。例如有4个并发任务要执行，
 * 正常情况下通过Future.get()获取，通常只能按照提交的顺序获得结果，
 * 如果最后提交的最先完成的话，总执行时间会长很多。而通过CompletionService能够降低总执行时间
 *
 *
 *
 * ps:【但是如果子线程执行完成后不需要执行其他任务，则意义不是很大】
 */
public class CompleteExecutorServiceTest {


    public static void main(String[] args) throws Exception {
//        test1();
        test2();
    }

    public static void test1() throws Exception{
        long start  = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> futureArrayList = new ArrayList<>();
        System.out.println("公司让你通知大家聚餐 你开车去接人");
        Future<String> future10 = executorService.submit(() -> {
            System.out.println("总裁：我在家上大号 我最近拉肚子比较慢 要蹲1个小时才能出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("总裁：1小时了 我上完大号了。你来接吧");
            return "总裁上完大号了";

        });
        futureArrayList.add(future10);
        Future<String> future3 = executorService.submit(() -> {
            System.out.println("研发：我在家上大号 我比较快 要蹲3分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("研发：3分钟 我上完大号了。你来接吧");
            return "研发上完大号了";
        });
        futureArrayList.add(future3);
        Future<String> future6 = executorService.submit(() -> {
            System.out.println("中层管理：我在家上大号  要蹲10分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(6);
            System.out.println("中层管理：10分钟 我上完大号了。你来接吧");
            return "中层管理上完大号了";
        });
        futureArrayList.add(future6);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("都通知完了,等着接吧。");
        try {
            for (Future<String> future : futureArrayList) {
                String returnStr = future.get();
                System.out.println(returnStr + "，你去接他");
            }
            System.out.println(System.currentTimeMillis()-start); //10056ms
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void test2() throws Exception {
        long start  = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        System.out.println("公司让你通知大家聚餐 你开车去接人");
        completionService.submit(() -> {
            System.out.println("总裁：我在家上大号 我最近拉肚子比较慢 要蹲1个小时才能出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("总裁：1小时了 我上完大号了。你来接吧");
            return "总裁上完大号了";
        });
        completionService.submit(() -> {
            System.out.println("研发：我在家上大号 我比较快 要蹲3分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("研发：3分钟 我上完大号了。你来接吧");
            return "研发上完大号了";
        });
        completionService.submit(() -> {
            System.out.println("中层管理：我在家上大号  要蹲10分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(6);
            System.out.println("中层管理：10分钟 我上完大号了。你来接吧");
            return "中层管理上完大号了";
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println("都通知完了,等着接吧。");
        //提交了3个异步任务）
        for (int i = 0; i < 3; i++) {
            String returnStr = completionService.take().get();
            System.out.println(returnStr + "，你去接他");
        }
        System.out.println(System.currentTimeMillis()-start);
        Thread.currentThread().join();
    }
}
