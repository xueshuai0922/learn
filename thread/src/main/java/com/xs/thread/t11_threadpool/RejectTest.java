package com.xs.thread.t11_threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xueshuai
 * @date 2022/4/22 9:16
 * @description  拒绝策略测试
 * 线程池中给的拒绝策略有四种
 *     1.调用的线程池的线程来执行
 *     2.抛出异常
 *     3.队列中把最早的任务进行抛弃
 *     4.静默处理
 */
public class RejectTest {

    public static void main(String[] args) {


        // 这里给了1个核心线程数，2个最大线程数，队列大小给了1个，也就是意味着消费中堆积我们超过了4个任务，第五个就要走拒绝策略了
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,2,100, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),new CustomRejectHandler()


        );
        //模拟多个任务执行任务
        for (int i = 0; i < 7; i++) {
            System.out.println("执行"+i);
            threadPoolExecutor.execute(()->{
                try {
                    Thread.sleep(10000);//模拟任务堆积
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程执行");
            });
        }



    }

}

class  CustomRejectHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println();
    }
}