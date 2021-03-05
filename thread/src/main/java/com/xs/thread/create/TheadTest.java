package com.xs.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

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
