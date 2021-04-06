package com.xs.thread.t07_juc.cyclebarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xueshuai
 * @date 2021/2/28 17:38
 * @description CycleBarrier 满足线程数，才会继续
 */
public class CycleBarrierTest1 {


    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("等了10个线程，结束了");
        });
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
