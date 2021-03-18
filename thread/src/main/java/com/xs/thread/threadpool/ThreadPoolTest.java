package com.xs.thread.threadpool;

import java.util.concurrent.*;

/**
 * @author xueshuai
 * @date 2021/3/12 16:08
 * @description
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 6000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(4), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
        //默认四种拒绝策略实现
        //AbortPolicy 抛出异常
        //CallerRunsPolicy 调用线程池的这个线程执行被拒绝的任务
        //DiscardPolicy 抛弃最后这个任务，然后不抛出异常
        //DiscardOldestPolicy 抛弃最早进入队列的任务

        for (int i = 0; i < 8; i++) {
            threadPoolExecutor.execute(new Task(i));
        }

        //上面我们设置最大线程数4个，任务队列中4个，所有总共的线程数8个，超过这个数量，走拒绝策略
        threadPoolExecutor.execute(new Task(9));
    }

    static  class Task implements  Runnable{
        private  int i ;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+ " task"+i);
        }
    }
}
