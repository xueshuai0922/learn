package com.xs.thread.t07_juc.safeproblem;

import java.util.concurrent.CountDownLatch;

/**
 * @author xueshuai
 * @date 2021/3/4 14:39
 * @description 通过 countdownlatch 线程顺序执行  实现线程A中执行线程B，线程B中执行线程C
 */
public class threadExample3 {
    static Thread cthread, bthread, athread;

    public static void main(String[] args) {
        CountDownLatch countDownLatchab = new CountDownLatch(1);
        CountDownLatch countDownLatchbc = new CountDownLatch(1);

        cthread = new Thread(() -> {
            countDownLatchbc.countDown();
            System.out.println("线程C");
        }, "c");

        bthread = new Thread(() -> {
            try {
                countDownLatchbc.await();
                System.out.println("线程B");
                countDownLatchab.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "b");


        athread = new Thread(() -> {
            try {
                countDownLatchab.await();
                System.out.println("线程A");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "a");


        athread.start();
        bthread.start();
        cthread.start();

    }
}
