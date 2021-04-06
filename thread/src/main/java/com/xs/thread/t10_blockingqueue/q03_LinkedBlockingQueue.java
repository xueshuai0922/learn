package com.xs.thread.t10_blockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xueshuai
 * @date 2021/3/22 17:18
 * @description
 */
public class q03_LinkedBlockingQueue {
    public static void main(String[] args) {
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>(5);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    queue.put(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                try {
                    System.out.println(queue.take());
                    System.out.println(queue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
