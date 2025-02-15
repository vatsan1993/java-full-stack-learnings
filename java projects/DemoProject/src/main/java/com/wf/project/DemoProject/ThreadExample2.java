package com.wf.project.DemoProject;

public class ThreadExample2 {
	public static void main(String[] args) {
		PrintService ps = new PrintService(5, 15);
		
		Thread pt = new Thread( new ProducerThread(ps));
		Thread ct = new Thread( new ConsumerThread(ps));
		pt.start();
		ct.start();
		try {
			pt.join();
			ct.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
//		join is used so that the system.out.prinln runs only after pt and ct are fully finished.
		System.out.println("All jobs done");
		
	}
}
