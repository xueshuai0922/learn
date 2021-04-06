package com.xs.thread.t07_juc.safeproblem;

/**
 * @author xueshuai
 * @date 2021/2/25 23:27
 * @description 同步测试1:电影票超卖
 */
public class SyncExample1 {
    private static int tickets=5;
    public static void main(String[] args) {
        Thread thread = new Thread(new RunImp(),"A");
        Thread thread1 = new Thread(new RunImp(),"B");
        Thread thread2 = new Thread(new RunImp(),"C");
        Thread thread3 = new Thread(new RunImp(),"D");
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();

    }
    public static class RunImp implements Runnable{
        @Override
        public   void run() {

                sale();
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    private synchronized static void sale() {

        for (int i = 0; i < 100; i++) {
            if(tickets>0){
                System.out.println(Thread.currentThread().getName()+"正在出售第"+(tickets--)+"张票");
            }
        }
    }
}
