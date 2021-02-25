package com.xs.thread.api;

import java.util.Date;

/**
 * @author xueshuai
 * @date 2021/2/25 22:18
 * @description yield 当前线程暂停一次执行，变为就绪状态
 */
public class YieldTest {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "----------------" + i);
            }
        });
        thread.start();


        for (int i = 0; i < 10; i++) {
            if(i==3){
                Thread.yield();
                System.out.println(Thread.currentThread().getName()+"---------"+i+"礼让一次");
            }else{
                System.out.println(Thread.currentThread().getName()+"---------"+i);
            }

        }
    }
}
