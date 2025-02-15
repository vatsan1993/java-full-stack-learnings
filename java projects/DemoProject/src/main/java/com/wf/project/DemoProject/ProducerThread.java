package com.wf.project.DemoProject;

public class ProducerThread implements Runnable{
	private PrintService ps ;
	
	public ProducerThread(PrintService ps) {
		this.ps  =ps;
	}

	@Override
	public void run() {
		try {
			ps.produceJobs();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
