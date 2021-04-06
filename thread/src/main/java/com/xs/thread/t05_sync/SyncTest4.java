package com.xs.thread.t05_sync;

/**
 * @author xueshuai
 * @date 2021/3/18 20:22
 * @description synchronized 等待线程不会被打断
 */
public class SyncTest4 {
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable run = () -> {
            synchronized (lock) {
                String name = Thread.currentThread().getName();
                System.out.println(name + "进入同步代码块");
                try {
                    // 让线程一持有锁3秒
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // 创建线程一先执行同步代码快
        Thread t1 = new Thread(run,"t1");
        t1.start();
        // 主线程睡眠一下，保证上面线程先执行
        Thread.sleep(1000L);
        // 后开启线程取执
        Thread t2 = new Thread(run,"t2");
        t2.start();
        System.out.println("开始中断线程二"); // 强行线程二中断
        t2.interrupt();
        System.out.println("线程一状态" + t1.getState());
        System.out.println("线程二状态" + t2.getState());//线程二状态BLOCKED 线程二没有被打断


        //线程1在获取锁后，睡眠了3秒，这里等待3秒加上之前的1秒，让线程2进入锁
        Thread.sleep(3000L);

        System.out.println("线程一状态" + t1.getState());
        System.out.println("线程二状态" + t2.getState());
    }
}
