package com.xs.thread.t01_create;

import java.util.concurrent.Callable;

/**
 * @author xueshuai
 * @date 2021/2/25 20:57
 * @description
 */
public class TheadTest {
    public static void main(String[] args) {
        //1.new thread
        Thread thread = new Thread();
        thread.start();
        //2.
        ExtendsThread extendsThread = new ExtendsThread();
        extendsThread.start();
        //
        Thread thread1 = new Thread(new ImplThread());
        thread1.start();
        //


    }


    public static class ExtendsThread extends  Thread{

    }
    public static class ImplThread implements Runnable{

        @Override
        public void run() {

        }
    }
    public static class ImplCallThread implements Callable{

        @Override
        public Object call() throws Exception {
            return null;
        }
    }


}
