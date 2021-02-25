package com.xs.thread.api;

import java.util.Date;

/**
 * @author xueshuai
 * @date 2021/2/25 22:06
 * @description sleep 让线程睡几秒，一般的主要作用是 让其他线程公平获取锁
 */
public class SleepTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "----------------" + i);
            }
        });
        thread.start();


        for (int i = 0; i < 10; i++) {
            if(i==3){
                System.out.println(new Date());
                Thread.sleep(1000*10);
                System.out.println(new Date());
            }
            System.out.println(Thread.currentThread().getName()+"---------"+i);

        }
    }
}
