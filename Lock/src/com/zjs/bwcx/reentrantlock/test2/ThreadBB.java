package com.zjs.bwcx.reentrantlock.test2;

public class ThreadBB extends Thread{
	private MyService myService;

	public ThreadBB(MyService myService) {
		super();
		this.myService = myService;
	}

	@Override
	public void run() {
		myService.methodB();
	}
	
}
