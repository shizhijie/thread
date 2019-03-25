package com.zjs.bwcx.reentrantlock.condition.erroruse;

public class ThreadA extends Thread{
	private MyService myService = new MyService();

	public ThreadA(MyService myService) {
		super();
		this.myService = myService;
	}

	@Override
	public void run() {
		myService.awaitA();
	}
	
}
