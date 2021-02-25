package com.xs.thread.api;

/**
 * @author xueshuai
 * @date 2021/2/25 22:43
 * @description 交替打印数字  利用sleep可以暂停线程这个特性进行
 */
public class SleepExample {

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            for (int i = 1; i < 11; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"========>"+i);
            }
        }).start();


        for (int i = 10; i > 0; i--) {
            Thread.sleep(1050);
            System.out.println(Thread.currentThread().getName()+"---------->"+i);
        }
    }
}
