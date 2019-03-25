package com.zjs.bwcx.reentrantlock.test2;

public class ThreadAA extends Thread{
	private MyService myService;

	public ThreadAA(MyService myService) {
		super();
		this.myService = myService;
	}

	@Override
	public void run() {
		myService.methodA();
	}
	
}
