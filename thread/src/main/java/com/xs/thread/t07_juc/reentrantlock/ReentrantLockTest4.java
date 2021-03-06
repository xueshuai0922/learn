package com.xs.thread.t07_juc.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xueshuai
 * @date 2021/3/17 22:45
 * @description
 */
public class ReentrantLockTest4 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();


        Thread t1 = new Thread(()->{
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted t1!");
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(()->{
            try {
//                lock.lock();
                lock.lockInterruptibly(); //可以对interrupt()方法做出响应
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted t2!");
            } finally {
                lock.unlock();
            }
        });
        t2.start();



        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt(); //打断线程2的等待

    }
}
