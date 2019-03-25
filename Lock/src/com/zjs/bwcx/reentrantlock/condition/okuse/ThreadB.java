package com.zjs.bwcx.reentrantlock.condition.okuse;

public class ThreadB extends Thread{
	private MyService myService = new MyService();

	public ThreadB(MyService myService) {
		super();
		this.myService = myService;
	}

	@Override
	public void run() {
		myService.awaitB();
	}
	
}
