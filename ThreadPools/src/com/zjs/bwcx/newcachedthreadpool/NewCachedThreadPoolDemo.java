package com.zjs.bwcx.newcachedthreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewCachedThreadPoolDemo {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 20; i++) {
			final int no = i;
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("into"+no);
						Thread.sleep(100000L);
						System.out.println("end"+no);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			newCachedThreadPool.execute(runnable);
		}
		newCachedThreadPool.shutdown();
		Thread.sleep(100000L);
		System.out.println("Main Thread End!!!");
	}
}
