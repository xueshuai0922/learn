package com.xs.thread.t10_blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;


//ֻ������take�ˣ������put  ��֤����Ϊ0
public class T08_SynchronusQueue { //����Ϊ0
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> strs = new SynchronousQueue<>();
		
		new Thread(()->{
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		strs.put("aaa"); //�����ȴ�����������
//		strs.put("bbb");
		//strs.put("bbb");
		//strs.add("aaa");
		System.out.println(strs.size());
	}
}
