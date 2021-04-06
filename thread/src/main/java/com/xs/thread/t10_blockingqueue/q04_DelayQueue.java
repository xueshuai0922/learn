package com.xs.thread.t10_blockingqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author xueshuai
 * @date 2021/3/23 10:06
 * @description 延迟消息队列
 */
public class q04_DelayQueue {
    public static void main(String[] args) throws InterruptedException {

        DelayQueue<Delayed> delayeds = new DelayQueue<>();
        task task = new task("task1",System.currentTimeMillis()+1000);
        task task2 = new task("task1",System.currentTimeMillis()+2000);
        task task3 = new task("task1",System.currentTimeMillis()+3000);
        delayeds.put(task);
        delayeds.put(task2);
        delayeds.put(task3);
        System.out.println(delayeds);
        for(;;){

            System.out.println(delayeds.take());
        }

    }


    static  class  task implements Delayed{
        private  String name;
        private  long time;

        public task() {
        }

        @Override
        public String toString() {
            return "task{" +
                    "name='" + name + '\'' +
                    ", time=" + time +
                    '}';
        }

        public task(String name, long time) {
            this.name = name;
            this.time = time;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(time-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS)<o.getDelay(TimeUnit.MILLISECONDS)) return -1;
            else if(this.getDelay(TimeUnit.MILLISECONDS)>o.getDelay(TimeUnit.MILLISECONDS))  return 1;
            else
            return 0;
        }
    }
}
