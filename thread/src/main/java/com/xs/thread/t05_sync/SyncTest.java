package com.xs.thread.t05_sync;

/**
 * @author xueshuai
 * @date 2021/2/27 21:38
 * @description 同步和非同步方法可以同时进行
 */
public class SyncTest {
    private  int s = 520;
    public synchronized void  sync(){

        s=1314;
        System.out.println("获取s的值本来是520，现在获取的值是"+s);
    }

    public  void  notSync(){
        System.out.println("获取s的值本来是520，现在获取的值是"+s);

    }

    public static void main(String[] args) {
        SyncTest syncTest = new SyncTest();


        new Thread(() -> {
            try {
                Thread.sleep(1000);
                syncTest.sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(syncTest::notSync).start();

    }
}
