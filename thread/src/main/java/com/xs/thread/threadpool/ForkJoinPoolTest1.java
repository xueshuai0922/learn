package com.xs.thread.threadpool;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author xueshuai
 * @date 2021/3/16 16:14
 * @description 利用ForkJoinPool计算一个大数组的和
 */
public class ForkJoinPoolTest1 {
    static long[] ints = new long[100000000];

    static {
        for (int i = 0; i < 100000000; i++) {
            ints[i]=i;
        }

    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        System.out.println(Arrays.stream(ints).sum());
        System.out.println("stream流的方式计算消耗的时间： "+(System.currentTimeMillis()-start)+"ms");

        long start2 = System.currentTimeMillis();
        System.out.println(Arrays.stream(ints).parallel().sum());
        System.out.println("parallelStream流的方式计算消耗的时间： "+(System.currentTimeMillis()-start2)+"ms");

        long start1 = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(0, ints.length);
        forkJoinPool.execute(countTask);
        System.out.println(countTask.join());
        System.out.println("ForkJoin的方式计算消耗的时间： "+(System.currentTimeMillis()-start1)+"ms");


    }

    static class CountTask extends RecursiveTask<Long> {
        int start;
        int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum=0;
            if(end-start<100000000/2){
                for (int i = start; i < end; i++) {
                    sum+=ints[i];
                }
                return sum;
            }
            int mid=start+(end-start)/2;
            CountTask countTask = new CountTask(start, mid);
            CountTask countTask1 = new CountTask(mid, end);
            countTask.fork();
            countTask1.fork();
            return countTask.join()+countTask1.join();
        }
    }
}
