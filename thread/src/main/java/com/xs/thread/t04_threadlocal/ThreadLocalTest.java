package com.xs.thread.t04_threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @author xueshuai
 * @date 2021/3/3 21:52
 * @description
 */
public class ThreadLocalTest {
    private  static  int s =1;

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();


        System.out.println();
        new Thread(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.set("xs handsome");
            System.out.println(threadLocal.get());//xs handsome
        }).start();

        new Thread(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());//null
        }).start();
    }
}
