package com.xs.thread.t02_lifetodie;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xueshuai
 * @date 2021/3/3 16:57
 * @description 线程优雅停止
 */
public class StopThreadTest {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getState());
             int i =0;
            while (true) {
                i++;
                if(i==200){
                    Thread.currentThread().interrupt();
                }
                //具体业务中，判断中断标志位，进行业务执行
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("run interrupt");
                    return;
                }
            }
        }, "a");
        thread.start();

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(
            () -> {
            // ... do something inside runnable task

        });
        service.shutdown();
        service.shutdownNow();

//        try {
//            TimeUnit.SECONDS.sleep(2);
//
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        //线程sleep两秒钟，进行打断线程，进行标志位
//        thread.interrupt();

    }
}
