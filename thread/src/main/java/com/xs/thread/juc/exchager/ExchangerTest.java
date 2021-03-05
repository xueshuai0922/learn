package com.xs.thread.juc.exchager;

import java.util.concurrent.Exchanger;

/**
 * @author xueshuai
 * @date 2021/2/28 19:42
 * @description Exchanger交换数据 t1 t2进行交换
 */
public class ExchangerTest {

    public static void main(String[] args) {

        //创建Exchanger
        Exchanger<String> exchanger = new Exchanger<>();

        //两个线程
        new Thread(() -> {
            try {
                String t1 = exchanger.exchange("t1");
                System.out.println(Thread.currentThread().getName()+"---->"+t1);// Thread1---->t2

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Thread1").start();


        new Thread(() -> {
            try {
                String t2 = exchanger.exchange("t2");

            System.out.println(Thread.currentThread().getName()+"---->"+t2);// Thread2---->t1

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Thread2").start();

    }
}
