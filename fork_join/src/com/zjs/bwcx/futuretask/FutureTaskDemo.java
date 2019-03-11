package com.zjs.bwcx.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		SonTask sonTask = new SonTask("Thread Son1");
		FutureTask<String> futureTask = new FutureTask<>(sonTask);
		new Thread(futureTask).start();
		System.out.println(futureTask.get());//只有得到返回结果才会往下面继续执行
		//执行完指定线程，返回指定结果
		FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new MyRun(), 22);
		new Thread(futureTask2).start();
		System.out.println("result_"+futureTask2.get());//只有执行完结果才会往下面执行
		System.out.println("Main Thread End!!!");
	}
}
class SonTask implements Callable<String>{
	
	private String name;
	public SonTask(String name) {
		this.name = name;
	}


	@Override
	public String call() throws Exception {
		Thread.sleep(1000L);
		System.out.println(name+"计算任务完成");
		return name+"--result1";
	}
	
}
class MyRun implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("特定线程2完成");
	}
	
}