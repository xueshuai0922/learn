package com.xs.thread.t07_juc.safeproblem;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xueshuai
 * @date 2021/3/4 14:39
 * @description 线程顺序执行  实现线程A中执行线程B，线程B中执行线程C
 */
public class ABCTest {
    static Thread cthread, bthread, athread;

    public static void main(String[] args) {
        cthread = new Thread(() -> {
            System.out.println("线程C开始");
            LockSupport.unpark(bthread);
            LockSupport.unpark(athread);
            System.out.println("线程C结束");
        }, "c");

        bthread = new Thread(() -> {
            System.out.println("线程B开始");
            cthread.start();

            LockSupport.park();
            System.out.println("线程B结束");

        }, "b");


        athread = new Thread(() -> {
            System.out.println("线程A开始");
            bthread.start();
            LockSupport.park();
            System.out.println("线程A结束");
        }, "a");


        athread.start();
    }
}
