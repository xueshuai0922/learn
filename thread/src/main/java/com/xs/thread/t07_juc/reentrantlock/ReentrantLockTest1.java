package com.xs.thread.t07_juc.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xueshuai
 * @date 2021/2/28 13:52
 * @description 重入锁
 */
public class ReentrantLockTest1 {

    public void m1(){
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println("A===>"+i);
            }
        } finally {
            lock.unlock();
        }
    }
    public void m2(){
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println("B===>"+i);
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest1 test1 = new ReentrantLockTest1();
        new Thread(test1::m1).start();
        new Thread(test1::m2).start();
    }

}
