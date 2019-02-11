package test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {
	
	private ConcurrentLinkedQueue<Task> workQueue;
	private ConcurrentHashMap<String, Object> resultMap;
	
	public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	@Override
	public void run() {
		while (true) {
			Task input = this.workQueue.poll();
			if (null == input) break;
			//真正的去做业务处理
			Object output = handle(input);
			this.resultMap.put(Integer.toString(input.getId()), output);
		}
	}

	private Object handle(Task input) {
		Object output = null;
		
		try {
			//表示处理task任务的耗时，可能是数据的加工，也可能是操所数据库。。。
			Thread.sleep(500);
			
			output = input.getPrice();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}
}
