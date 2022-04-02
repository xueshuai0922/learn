package com.xs.thread.t12_thread_complete_future;

import java.util.concurrent.CompletableFuture;

/**
 * @author xueshuai
 * @date 2022/3/23 20:14
 * @description
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(Main::fetchPrice);
        // 暂停让Future完成:
        Thread.sleep(500);
        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("thenAccept: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    static double fetchPrice() {
        System.out.println("fetched!");
        return 123.456;
    }
}
