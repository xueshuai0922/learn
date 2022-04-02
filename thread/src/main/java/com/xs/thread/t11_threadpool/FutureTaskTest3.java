package com.xs.thread.t11_threadpool;

import java.util.concurrent.*;

/**
 * @author xueshuai
 * @date 2021/9/17 14:56
 * @description
 */
public class FutureTaskTest3 {



    //当两个线程试图同时执行同一个任务时，如果 Thread 1 执行 1.3 后 Thread 2 执行
    //2.1，那么接下来 Thread 2 将在 2.2 等待，直到 Thread 1 执行完 1.4 后 Thread 2 才能从 2.2
    //（FutureTask.get()）返回。
    private final ConcurrentMap<Object, Future<String>> taskCache = new
            ConcurrentHashMap<Object, Future<String>>();

    private String executionTask(final String taskName) throws ExecutionException,
            InterruptedException {
        while (true) {
            Future<String> future = taskCache.get(taskName); // 1.1,2.1
            if (future == null) {
                Callable<String> task = new Callable<String>() {
                    public String call() throws InterruptedException {
                        return taskName;
                    }
                };
                FutureTask<String> futureTask = new FutureTask<String>(task);
                future = taskCache.putIfAbsent(taskName, futureTask); // 1.3
                if (future == null) {
                    future = futureTask;
                    futureTask.run(); // 1.4 执行任务
                }
            }
            try {
                return future.get(); // 1.5,
            } catch (
                    CancellationException e) {
                taskCache.remove(taskName, future);
            }
        } }
}
