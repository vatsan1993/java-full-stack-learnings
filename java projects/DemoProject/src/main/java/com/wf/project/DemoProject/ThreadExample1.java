package com.wf.project.DemoProject;

public class ThreadExample1 {
	public static void main(String[] args) {
		// This program has 2 threads acting on 2 different resources.
		System.out.println(Thread.currentThread());
		
		NumberGenerator gen1 = new NumberGenerator(10);
		NumberGenerator gen2 = new NumberGenerator(10);
		
		Thread t1 = new Thread(gen1);
		Thread t2 = new Thread(gen2);
		t1.start();
		t2.start();
		
		System.out.println("finished program"); // the main program does not wait till the threads are finished running
		
	}
}
