package com.xs.thread.sync;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xueshuai
 * @date 2021/2/27 22:58
 * @description 异常会释放锁
 */
public class SyncTest3 {
    private static int s = 0;
    public static synchronized void m1(){

        for (int i = 0; i < 10; i++) {

            s++;
            //等于5的时候，会释放锁，正常来说，会执行完
            if(i==5){
                int s= 10/0;
            }
            System.out.println(Thread.currentThread().getName()+"-----"+s);
        }
    }

    public static void main(String[] args) {
        new Thread(()->{
            m1();
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                s++;
                System.out.println(Thread.currentThread().getName()+"-----"+s);
            }
        },"BB").start();
    }

}
