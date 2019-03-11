package com.zjs.bwcx.forkjoin;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTaskDemo {
	
	public static void main(String[] args) {
		Integer count = new ForkJoinPool().invoke(new CountTask1(Paths.get("D:/opt")));
		System.out.println("D：/opt盘下面总文件数量："+count);
		System.out.println("Thread Main End!!!");
	}
}

class CountTask1 extends RecursiveTask<Integer>{
	
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;
	private Path dir;
	public CountTask1(Path dir) {
		super();
		this.dir = dir;
	}


	@Override
	protected Integer compute() {
		int count = 0;
		List<CountTask1> subTasks = new ArrayList<>();
		//试图读取dir的子路径
		try {
			DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(dir);
			for (Path subPath:newDirectoryStream) {
				if (Files.isDirectory(subPath, LinkOption.NOFOLLOW_LINKS)) {
					//对每个子目录都新建一个子任务
					subTasks.add(new CountTask1(subPath));
				}else {
					//遇到文件 则计数器加1
					count++;
				}
			}
			if (!subTasks.isEmpty()) {
				//在当前的ForkJoinPool上调度所有的子任务
				for (CountTask1 sTask1 : invokeAll(subTasks)) {
					count += sTask1.join();
				}
			}
		} catch (IOException e) {
			return 0;
		}
		return count;
	}
	
}
