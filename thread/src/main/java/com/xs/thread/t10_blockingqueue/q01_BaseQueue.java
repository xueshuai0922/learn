package com.xs.thread.t10_blockingqueue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xueshuai
 * @date 2021/3/22 14:38
 * @description
 */
public class q01_BaseQueue {
    public static void main(String[] args) {
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>(1);


        boolean add = queue.add(1);//如果队列满了，报错 对应remove方法
        boolean offer = queue.offer(2);//队列满了也不会报错 对应poll
        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue.remove());
        System.out.println(queue.poll());//poll没有值移除，返回空

        System.out.println(queue.remove());

//
//        System.out.println(queue);
//        System.out.println("peek获取值："+queue.peek());
//        System.out.println("PEEK后 "+queue);
//        Object poll = queue.poll();
//        System.out.println(poll);
//        System.out.println(queue);



    }
}
