package com.xs.thread.t07_juc.countdownlatch;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author xueshuai
 * @date 2021/2/28 16:53
 * @description 等待线程结束
 */
public class CountDownLatchTest1 {


    public static void main(String[] args) {

        CountDownLatch count = new CountDownLatch(10);
        ArrayList<Thread> threads = new ArrayList<>();
        //创建10个线程，
        for (int i = 0; i < 15; i++) {
            Thread thread = new Thread(() -> {

                try {
                    System.out.println("countdown");
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //在线程run方法中，也就是说，每次线程执行任务完后，countdown一次
                count.countDown();
            });
            threads.add(thread);
        }
        System.out.println("latch start ");
        threads.forEach(Thread::start);



        try {
            //一直等待这countdown为0，倒计时完成后，才进行下面的程序
//            count.await();
            boolean await = count.await(1, TimeUnit.SECONDS);
            System.out.println("线程计数是否完成"+await);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("latch end ");
    }
}
