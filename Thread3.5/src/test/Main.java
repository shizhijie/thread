package test;

import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		
		Master master = new Master(new Worker(), 10);
		Random r = new Random();
		for (int i = 1; i <= 100; i++) {
			Task t = new Task();
			t.setId(i);
			t.setName("任务"+i);
			t.setPrice(r.nextInt(1000));
			master.submit(t);
		}
		master.execute();
		while (true) {
			if (master.isComplete()) {
				long ret = master.getResult();
			}
			
		}
	}
}
