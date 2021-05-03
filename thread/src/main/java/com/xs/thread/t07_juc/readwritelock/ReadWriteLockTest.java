package com.xs.thread.t07_juc.readwritelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xueshuai
 * @date 2021/2/28 18:12
 * @description  测试内容：读写锁和 重入锁的 速度对比
 *              结果：读写锁速度快，读写锁，多个线程读只需sleep1秒中。而ReentrantLock需要每个线程都sleep一秒
 */
public class ReadWriteLockTest {

    private  static  int ss = 0;
    public static  void read(Lock lock){
        try {
            //如果这个为读锁，那么多线程进来，读操作不会被阻塞，所以多个线程一起进来，只会整体sleep一秒钟，然后，都读到数据
            lock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("读取完成,值为： "+ss);
        } finally {
            lock.unlock();
        }


    }

    public static  void write(Lock lock,int i){
        try {
            lock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ss=i;
            System.out.println("写完成");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
         Lock readLock = readWriteLock.readLock();
         Lock writeLock = readWriteLock.writeLock();

        for (int i = 0; i <10 ; i++) {
            //读线程
            new Thread(()->{
                read(readLock);
//                read(lock);
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            //写线程
            new Thread(()->{
                write(writeLock,55);
//                write(lock,55);
            }).start();
        }
    }
}
