package com.zjs.bwcx.newfixedthreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPoolDemo {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 20; i++) {
			final int no = i;
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("into"+no);
						Thread.sleep(10000L);
						System.out.println("end"+no);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			newFixedThreadPool.execute(runnable);
		}
		newFixedThreadPool.shutdown();
		Thread.sleep(10000L);
		System.out.println("Main Thread End!!!");
	}
}
