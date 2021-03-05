package com.xs.thread.voliatile;

/**
 * @author xueshuai
 * @date 2021/2/28 0:01
 * @description volatile 测试，只是可见性，没有原子性
 */
public class VolatileTest1 {
    private  static  volatile  int s =0;

    public static void main(String[] args) {
        new Thread(()->{
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 1000; i++) {
                s++;
            }
            System.out.println(s);
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 1000; i++) {
                s++;
            }
        }).start();


    }
}
