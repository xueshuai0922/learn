package com.xs.thread.t10_blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xueshuai
 * @date 2021/3/20 15:22
 * @description condition测试  必须在lock下进行await,signal
 */
public class ConditionTest {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Thread thread = new Thread(() -> {
            reentrantLock.lock();
            System.out.println("开始等待");
            try {
                condition.await();
                System.out.println("t1线程的状态"+Thread.currentThread().getState());//runnable
                System.out.println("等待结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
            reentrantLock.unlock();
        }, "t1");
        thread.start();


        new Thread(()->{
            reentrantLock.lock();

            try {
                System.out.println("t1线程的状态"+thread.getState());//waiting
                System.out.println("开始唤醒");
                condition.signalAll();

                System.out.println("唤醒结束");
            } catch (Exception e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        },"t2").start();
    }
    //执行顺序:
    //  开始等待
    //  开始唤醒
    //  唤醒结束
    //  等待结束

}
