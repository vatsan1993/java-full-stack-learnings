package com.wf.project.DemoProject;

import java.util.Random;

public class NumberGenerator implements Runnable {
	
	private Random rand;
	private int count;
	
	public NumberGenerator(int count) {
	
		rand = new Random();
		this.count = count;
		
	}

	public void generateSeries() {
		String threadName = Thread.currentThread().getName();
		
		for(int i = 0; i < count; i++) {
			System.out.println("Thread: "+ threadName+ " Random value: " + rand.nextInt(100 ));
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run() {
		generateSeries();
	}
}
