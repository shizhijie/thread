package com.zjs.bwcx.reentrantlock.test2;

public class ThreadB extends Thread{
	private MyService myService;

	public ThreadB(MyService myService) {
		super();
		this.myService = myService;
	}

	@Override
	public void run() {
		myService.methodB();
	}
	
}
