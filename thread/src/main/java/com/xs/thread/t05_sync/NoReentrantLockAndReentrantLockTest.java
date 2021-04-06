package com.xs.thread.t05_sync;

/**
 * @author xueshuai
 * @date 2021/2/28 14:15
 * @description  自定义可重入和不可重入锁进行验证
 */
public class NoReentrantLockAndReentrantLockTest {


    //=================================不可重入锁====================================
    public static class NoReentrantLock{
        //是否锁上
        private  boolean isLock=false;
        //加锁
        public void lock(){
            while(isLock=true){
                try {
                    //如果锁上了，就等着被解锁
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isLock=true;
        }
        //解锁
        public void unlock(){
            isLock=false;
        }

    }

    public static class NoRe{


        //==============不可重入锁测试==============
        NoReentrantLock lock = new NoReentrantLock();
        public void m1(){
            System.out.println("进入m1方法中了");
            try {
                lock.lock();
                System.out.println("进入m1方法中了");
                notify();
                m2();
            }finally {
                lock.unlock();
            }
        }
        public void  m2(){
            try {
                lock.lock();
                System.out.println("进入m2方法中了========");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        NoRe notest = new NoRe();

        new Thread(()->{
            notest.m1();
        }).start();

    }

}
