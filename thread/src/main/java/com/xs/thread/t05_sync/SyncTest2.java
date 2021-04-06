package com.xs.thread.t05_sync;

/**
 * @author xueshuai
 * @date 2021/2/27 22:30
 * @description 验证synchronized可重入锁。，方法都有锁 todo
 */
public class SyncTest2 {

    public synchronized void m1() {
            System.out.println("父类synchronize方法" );
            m2();
    }

    public void m2() {
        System.out.println("子类synchronize方法");
    }

    public static void main(String[] args) {
        SyncTest2 syncTest2 = new SyncTest2();
        new Thread(syncTest2::m1).start();
    }


}
