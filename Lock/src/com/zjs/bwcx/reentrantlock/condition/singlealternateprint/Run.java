package com.zjs.bwcx.reentrantlock.condition.singlealternateprint;

public class Run {
	public static void main(String[] args) {
		MyService myService = new MyService();
		MyThreadA myThreadA = new MyThreadA(myService);
		myThreadA.start();
		MyThreadB myThreadB = new MyThreadB(myService);
		myThreadB.start();
	}
}
