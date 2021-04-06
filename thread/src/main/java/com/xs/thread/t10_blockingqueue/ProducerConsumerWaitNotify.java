package com.xs.thread.t10_blockingqueue;

import java.util.LinkedList;

/**
 * @author xueshuai
 * @date 2021/3/18 13:20
 * @description 生产者-消费模式的测试  ===》阻塞队列测试
 *
 *          生活场景：面包店柜台 10个面包，当柜台满了10个出售（就一定要满足10个要求）
 *          多个面包点师傅（生产者）：每个生产者可以一次生产5个面包，当柜台有10个了，那么面包店师傅就停下来不做
 *          多个顾客（消费者）：每个消费者可以购买多个面包，当柜台没有面包了，通知面包店师傅
 */
public class ProducerConsumerWaitNotify {
    static  LinkedList  linkedList = new LinkedList();
    static  int MAX=10;//假定我们最多生产10个,10个后就进行阻塞了
    static  int count=0;

    public static void main(String[] args) {
        ProducerConsumerWaitNotify pc = new ProducerConsumerWaitNotify();
        for (int i = 0; i < 20; i++) {

            new Thread(pc::producer,"producer"+i).start();
        }
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        for (int i = 0; i < 3; i++) {
            new Thread(pc::consumer,"consumer"+i).start();
        }
    }

    private synchronized   void producer() {

        while (linkedList.size() == MAX) {
            try {
                System.out.println("生产满了，停下来，让消费");
                System.out.println("wait");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        linkedList.add(Thread.currentThread().getName() + " " + count++);
        this.notifyAll();
    }

    private synchronized   void consumer() {
        for (int q = 0; q < 10; q++) {
            while (linkedList.size() == 0) {
                System.out.println("消耗完了，继续生产");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Object o = linkedList.removeFirst();
            System.out.println(Thread.currentThread().getName()+" 消费了： " + o);
            this.notifyAll();
        }

    }
}
