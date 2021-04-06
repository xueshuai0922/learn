package com.xs.thread.t07_juc.safeproblem;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xueshuai
 * @date 2021/3/9 8:50
 * @description 两个线程交替输出A1B2C3
 *         1.创建两个线程，两个字符数组，12345……9 和ABC……D
 *         2.处理线程方式：
 *          1）synchronized   wait notify
 *          2)lockSupport park unPark
 *          3)TransferQueue
 */
public class A1B2C3Test {
    static char[] chars = {'A', 'B', 'C', 'D','E','F','G','H','J'};
    static char[] nums = {'1', '2', '3', '4','5','6','7','8','9'};
    static Thread t1;
    static Thread t2;

    public static void main(String[] args) {

//        LockSupport();
//        Sync();
        transfer();
    }


    private static void LockSupport() {
        t1 = new Thread(() -> {
            for (char c : nums) {
                LockSupport.park();//当前线程阻塞
                System.out.print(c);
                LockSupport.unpark(t2);
            }
        }, "t1");


        t2 = new Thread(() -> {
            for (char c : chars) {
                System.out.print(c);
                LockSupport.unpark(t1);//使t1进行启动
                LockSupport.park();
            }
        }, "t2");

        t2.start();
        t1.start();
    }


    private  static  void Sync(){
        Object o = new Object();
        //调用某个对象的notify()方法能够唤醒一个正在等待这个对象的monitor的线程，
        // 如果有多个线程都在等待这个对象的monitor，则只能唤醒其中一个线程
        t1 = new Thread(() -> {
            synchronized (o){
                for (char c : nums) {
                    try {
                        System.out.print(c);//1.输出字符
                        o.notify();//2.唤醒等待这个锁的线程t2
                        o.wait();//3.释放了锁，处于waiting状态，由t2进行获取了锁

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                o.notify();
            }
        }, "t1");


        t2 = new Thread(() -> {
            synchronized (o){
                for (char c : chars) {
                    try {
                        System.out.print(c);//4.输出字符
                        o.notify();//5.唤醒等待这个锁的线程t1
                        o.wait();//6.当前线程释放锁，处于等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                o.notify();//循环完，线程可能处于wait状态，需要唤醒
            }
        }, "t2");

        t1.start();
        t2.start();
    }

    private static  void  transfer(){

        //TransferQueue 是个容量为0的阻塞队列，主要就是手递手传递值的作用  transfer
        TransferQueue<Character> queue = new LinkedTransferQueue<>();

        t1 = new Thread(() -> {
                for (char c : nums) {
                    try {
                        Character take = queue.take();
                        System.out.print(take);//1.take一直阻塞，等待queue中的值  3.t2获取值并输出字母
                        queue.tryTransfer(c);//5. 传送数字
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
        }, "t1");


        t2 = new Thread(() -> {
                for (char c : chars) {
                    try {
                        queue.tryTransfer(c);//2. t2进行传送一个值
                        Character take = queue.take();
                        System.out.print(take);// 4. t2等待获取值  6.获取数字
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }, "t2");

        t1.start();
        t2.start();

    }
}
