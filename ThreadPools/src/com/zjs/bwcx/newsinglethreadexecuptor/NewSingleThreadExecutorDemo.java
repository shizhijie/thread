package com.zjs.bwcx.newsinglethreadexecuptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewSingleThreadExecutorDemo {
	
	public static void main(String[] args) {
		//使用方法
		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			final int no = i;
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("into"+no);
						Thread.sleep(1000L);
						System.out.println("end"+no);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			newSingleThreadExecutor.execute(runnable);
		}
		newSingleThreadExecutor.shutdown();
		System.out.println("Thread Main End!");
	}
}
