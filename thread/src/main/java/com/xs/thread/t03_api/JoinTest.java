package com.xs.thread.t03_api;

import java.util.Date;

/**
 * @author xueshuai
 * @date 2021/2/25 21:33
 * @description join顾明思议 加入进来，调用的线程加入进来，强制来执行，其他线程阻塞中,join就是有了优先选择CPU权利
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread joinThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "----------------" + i);
//                try {
//                    System.in.read();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
        joinThread.start();


        for (int i = 0; i < 10; i++) {
            if(i==3){
                System.out.println(new Date());
                //让joinThread这个类执行10s  其他线程阻塞
                joinThread.join(10*1000);
                System.out.println(new Date());
                System.out.println(Thread.currentThread().getState());
            }
            System.out.println(Thread.currentThread().getName()+"---------"+i);

        }
    }
}
