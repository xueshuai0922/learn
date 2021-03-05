package com.xs.thread.juc.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xueshuai
 * @date 2021/2/28 16:23
 * @description 公平锁和非公平锁
 *              新来的线程是否也放入等待获取锁的队列中 为 公平非公平的评判标准
 */
public class ReentrantLockTest2 implements  Runnable{

    //true 为公平锁
    public static ReentrantLock lock =new ReentrantLock(false);
    @Override
    public void run() {

        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"----->"+i);
            }
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        ReentrantLockTest2 r = new ReentrantLockTest2();
        Thread thread = new Thread(r, "a");
        Thread thread1 = new Thread(r, "b");
        thread.start();
        thread1.start();


    }
}
