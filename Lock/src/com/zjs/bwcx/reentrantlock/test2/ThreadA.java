package com.zjs.bwcx.reentrantlock.test2;

public class ThreadA extends Thread{
	
	private MyService myService;
	public ThreadA(MyService myService) {
		super();
		this.myService = myService;
	}

	@Override
	public void run() {
		myService.methodA();
	}
	
	
}
