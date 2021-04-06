package com.xs.thread.t10_blockingqueue;

import javax.lang.model.element.VariableElement;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xueshuai
 * @date 2021/3/18 13:20
 * @description 生产者-消费模式的测试  ===》阻塞队列测试
 *           ReentrantLock
 *
 *
 *
 */
public class ProducerConsumerCondition {
    static  LinkedList  linkedList = new LinkedList();
    static  int MAX=2;//假定我们最多生产10个,10个后就进行阻塞了
    static  int count=0;
    static ReentrantLock lock= new ReentrantLock();
    static Condition consumer = lock.newCondition();
    static Condition producer = lock.newCondition();

    public static void main(String[] args) {

        ProducerConsumerCondition pc = new ProducerConsumerCondition();
        for (int i = 0; i < 10; i++) {
            new Thread(pc::consumer,"consumer"+i).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(pc::producer,"producer"+i).start();
        }


    }

    private   void producer() {
        lock.lock();
        try {
            while (linkedList.size() == MAX) {
                try {
                    System.out.println("生产满了，停下来，让消费");
                    System.out.println("wait");
                    producer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            linkedList.add(Thread.currentThread().getName() + " " + count++);
            consumer.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private synchronized   void consumer() {
        lock.lock();
        try {
            for (int q = 0; q < 10; q++) {
                while (linkedList.size() == 0) {
                    System.out.println("消耗完了，继续生产");
                    try {
                        consumer.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Object o = linkedList.removeFirst();
                System.out.println(Thread.currentThread().getName()+" 消费了： " + o);
                producer.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
