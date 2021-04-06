package com.xs.thread.t11_threadpool;

import java.util.concurrent.*;

/**
 * @author xueshuai
 * @date 2021/3/9 11:24
 * @description  了解基本接口的方法，
 */
public class ExecutorTest implements Executor{

    public static void main(String[] args) {

        //接口执行器，有个执行方法
        ExecutorTest executorTest = new ExecutorTest();
        executorTest.execute(() ->
                System.out.println("this is a implements method executor"));

    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
