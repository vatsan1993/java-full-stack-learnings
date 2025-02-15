package com.wf.project.DemoProject;

public class ConsumerThread implements Runnable {
	PrintService ps;
	public ConsumerThread(PrintService ps) {
		this.ps  = ps;
	}
	@Override
	public void run() {
		try {
			ps.consomeJobs();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}	
