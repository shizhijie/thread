package com.zjs.bwcx.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinClassDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		CountTask task = new CountTask(1, 5);
		ForkJoinTask<Integer> result = forkJoinPool.submit(task);
		System.out.println("1-5最终相加的结果是："+result.get());
		CountTask task2 = new CountTask(1, 100);
		ForkJoinTask<Integer> result2 = forkJoinPool.submit(task2);
		System.out.println("1-100最终相加的结果是："+result2.get());
		System.out.println("Main Thread End!!!");
	}
}
class CountTask extends RecursiveTask<Integer>{
	
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = -4892514517801842859L;
	private static int splitSize = 2;
	private int start,end;
	

	public CountTask(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	/**
	 * ForkJoinTask与一般的任务的主要区别在于他需要实现compute方法
	 * 在这个方法里，首先要判断任务是否足够小，如果足够小的话就直接
	 * 执行任务，如果不足够小的，就必须分割成两个子任务，每个子任务
	 * 在调用fork方法时，又会进入compute方法，看看当前子任务是否需
	 * 要继续分割成孙任务，如果不需要继续分割，则执行当前子任务并返
	 * 回结果。使用join方法会等待子任务执行完成并得到其结果。
	 */
	@Override
	protected Integer compute() {
		int sum = 0;
		//如果任务已经不需要在拆分了就开始计算
		boolean canCompute = (end-start)<=splitSize;
		if (canCompute) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		}else {
			//拆分成两个子任务
			int middle = (start+end)/2;
			System.out.println("start:"+start);
			System.out.println("end:"+end);
			System.out.println("middle:"+middle);
			CountTask cTask1 = new CountTask(start, middle);
			CountTask cTask2 = new CountTask(middle+1, end);
			cTask1.fork();
			cTask2.fork();
			//获得第一个任务的结果，得不到结果，此线程不会往下面执行
			Integer firstResult = cTask1.join();
			Integer secondResult = cTask2.join();
			//System.out.println("firstResult:"+firstResult);
			//System.out.println("secondResult:"+secondResult);
			//合并两个儿子的执行结果
			sum = firstResult + secondResult;
		}
		return sum;
	}
	
}
