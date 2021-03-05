package com.xs.thread.juc.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author xueshuai
 * @date 2021/2/28 18:44
 * @description  Semaphore 信号 ，指定几个线程同时进行
 */
public class SemaphoreTest {


    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("t1 start");
                Thread.sleep(200);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();

        new Thread(()->{
            try {
                semaphore.acquire();//
                System.out.println("t2 start");
                Thread.sleep(200);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();
    }
}
