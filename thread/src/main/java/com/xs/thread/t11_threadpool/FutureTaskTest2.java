package com.xs.thread.t11_threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xueshuai
 * @date 2021/4/12 13:58
 * @description 测试FutureTask功能： 可以cancel和get结果
 */
public class FutureTaskTest2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>(()->{
            //停一秒，模拟计算操作
            Thread.sleep(1000);
            System.out.println("futuretask 实现了future接口，可以进行get结果和cancel取消任务，也" +
                    "实现了接口Runnable，可以进行当做子任务进行run（）");
            return "futureTask 结果";
        });

        Thread thread = new Thread(futureTask);
        thread.start();
        Object o = futureTask.get();//get 会进行阻塞，一直等到任务结束
        System.out.println(o+"");

    }
}
