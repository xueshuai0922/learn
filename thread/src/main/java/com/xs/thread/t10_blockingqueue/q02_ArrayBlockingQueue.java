package com.xs.thread.t10_blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author xueshuai
 * @date 2021/3/22 15:32
 * @description 固定的队列，有界
 */
public class q02_ArrayBlockingQueue {

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        //设置5个值进去
        for (int i = 0; i < 5; i++) {
            arrayBlockingQueue.put(i);
        }
        System.out.println(arrayBlockingQueue);
        arrayBlockingQueue.offer(7);
//        arrayBlockingQueue.add(7); 报错
        arrayBlockingQueue.put(7);//阻塞 ，等待中

    }
}
