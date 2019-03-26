package com.zjs.bwcx.reentrantlock.condition.manyalternateprint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean hasValue = false;
	public void set() {
		try {
			lock.lock();
			while(hasValue==true) {
				System.out.println("有可能★★连续");
				condition.await();
			}
			System.out.println("打印★");
			hasValue = true;
			//condition.signal();
			condition.signalAll();//解决假死
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	public void get() {
			try {
				lock.lock();
				while(hasValue==false) {
					System.out.println("有可能☆☆连续");
					condition.await();
				}
				System.out.println("打印☆");
				hasValue = false;
				//condition.signal();
				condition.signalAll();//解决家死
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
	}
}
