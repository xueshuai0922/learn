package com.xs.thread.t10_blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;


//只有消费take了，后才能put  保证容量为0
public class T08_SynchronusQueue { //容量为0
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> strs = new SynchronousQueue<>();
		
		new Thread(()->{
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		strs.put("aaa"); //阻塞等待消费者消费
//		strs.put("bbb");
		//strs.put("bbb");
		//strs.add("aaa");
		System.out.println(strs.size());
	}
}
