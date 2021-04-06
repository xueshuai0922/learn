package com.xs.thread.t08_locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xueshuai
 * @date 2021/3/16 13:48
 * @description LockSupport unpark释放了一个不停止的许可，后面遇到有个要停止的要求也不会停止
 *                  park()和unpark()的执行效果和它调用的先后顺序没有关系
 */
public class LockSupportTest1 {

    public static void main(String[] args) {
        System.out.println("unlock");
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park(Thread.currentThread());
        System.out.println("lock");


        //反面教材，如果这个线程先复活，然后再暂停，这个线程就一直处于卡死
        Thread.currentThread().resume();
        Thread.currentThread().suspend();

    }
}
