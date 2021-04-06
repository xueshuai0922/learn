package com.xs.thread.t10_blockingqueue;

import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class T07_01_PriorityBlockingQueue {
    public static void main(String[] args) {
        //按照默认升序进行compare
        PriorityBlockingQueue<String> q = new PriorityBlockingQueue<>(3);

        q.add("c");
        q.add("e");
        q.add("a");
        q.add("d");
        q.add("z");

        for (int i = 0; i < 5; i++) {
            try {
                System.out.print(q.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
