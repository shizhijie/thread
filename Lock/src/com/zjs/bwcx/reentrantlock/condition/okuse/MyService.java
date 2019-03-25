package com.zjs.bwcx.reentrantlock.condition.okuse;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
	private Lock lock = new ReentrantLock();
	public Condition conditionA = lock.newCondition();
	public Condition conditionB = lock.newCondition();
	
	public void awaitA() {
		try {
			System.out.println("begin awaitA开始时间为"+System.currentTimeMillis()+
					" ThreadName="+Thread.currentThread().getName());
			lock.lock();
			conditionA.await();
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
			conditionB.await();
			System.out.println("end awaitB时间为"+System.currentTimeMillis()+
					" ThreadName="+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void signalAll_A() {
		try {
			lock.lock();
			System.out.println(" signalAll_A时间为"+System.currentTimeMillis()+
					" ThreadName="+Thread.currentThread().getName());
			conditionA.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public void signalAll_B() {
		try {
			lock.lock();
			System.out.println(" signalAll_B时间为"+System.currentTimeMillis()+
					" ThreadName="+Thread.currentThread().getName());
			conditionB.signalAll();
		} finally {
			lock.unlock();
		}
	}
}
