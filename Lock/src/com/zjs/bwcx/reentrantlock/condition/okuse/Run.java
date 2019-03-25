package com.zjs.bwcx.reentrantlock.condition.okuse;


public class Run {
	public static void main(String[] args) throws InterruptedException {
		MyService myService = new MyService();
		ThreadA a = new ThreadA(myService);
		a.setName("A");
		a.start();
		ThreadB b = new ThreadB(myService);
		b.setName("B");
		b.start();
		Thread.sleep(3000);
		myService.signalAll_A();
	}
}
