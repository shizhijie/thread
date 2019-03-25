package com.zjs.bwcx.reentrantlock.condition.erroruse;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
	private Lock lock = new ReentrantLock();
	public Condition condition = lock.newCondition();
	public void awaitA() {
		try {
			System.out.println("begin awaitA开始时间为"+System.currentTimeMillis()+
					" ThreadName="+Thread.currentThread().getName());
			lock.lock();
			condition.await();
			System.out.println("end awaitA开始时间为"+System.currentTimeMillis()+
					" ThreadName="+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void awaitB() {
		try {
			System.out.println("begin awaitB时间为"+System.currentTimeMillis()+
					" ThreadName="+Thread.currentThread().getName());
			lock.lock();
			condition.await();
			System.out.println("end awaitB时间为"+System.currentTimeMillis()+
					" ThreadName="+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void signalAll() {
		try {
			lock.lock();
			System.out.println(" signalAll时间为"+System.currentTimeMillis()+
					" ThreadName="+Thread.currentThread().getName());
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
}
