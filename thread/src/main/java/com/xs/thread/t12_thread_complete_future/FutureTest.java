package com.xs.thread.t12_thread_complete_future;

import java.util.concurrent.*;

/**
 * @author xueshuai
 * @date 2022/1/25 15:09
 * @description future 作用： 调用线程可以继续运行，不会因为调用方法阻塞
 * <p>
 * 这种编程方式让你的线程可以在ExecutorService以并发方式调用另一个线程执行耗时操作的同时，去执行一些其他的任务。
 * 接着，如果你已经运行到没有异步操作的结果就无法继续任何有意义的工作时，可以调用它的get方法去获取操作的结果。
 * 如果操作已经完成，该方法会立刻返回操作的结果，否则它会阻塞你的线程，直到操作完成，返回相应的结果
 *
 *
 *
 * 一句话：很多事情要做，分给其他人来做（异步），其他人做完【主动】发来信息事件（回调）
 */
public class FutureTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<?> submit1 = executorService.submit(() -> {
            try {
                //模拟异常，异常了，会阻塞get
//                int i = 1/0;
                Thread.sleep(2000);
                System.out.println("A working");


            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Future<?> submit2 = executorService.submit(() -> {
            try {
                //模拟异常，异常了，会阻塞get
//                int i = 1/0;
                Thread.sleep(2000);
                System.out.println("B working");


            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Long start  = System.currentTimeMillis();
        submit1.get(10, TimeUnit.SECONDS);//get会阻塞等待
        submit2.get(10, TimeUnit.SECONDS);//get会阻塞等待
        Long end  = System.currentTimeMillis();

        System.out.println("消耗（s）:"+(end-start)/1000);
        System.out.println("线程A干着事情，现在这边异步可以再做其他事情");


        executorService.shutdown();
    }


}
