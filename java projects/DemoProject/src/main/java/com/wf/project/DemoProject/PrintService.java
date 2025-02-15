package com.wf.project.DemoProject;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PrintService {
	List<Integer> printJobs;
	int jobCount;
	int maxJobCount;

	public PrintService(int maxJobCount, int jobCount) {
		this.maxJobCount = maxJobCount;
		this.jobCount = jobCount;
		this.printJobs = new LinkedList<Integer>();
	}

	public void produceJobs() throws InterruptedException {
		for (int i = 0; i < jobCount; i++) {
			synchronized (this) {
				while (printJobs.size() >= maxJobCount) {
//					Can only only perform 5 jobs at a time before it goes to sleep.
					wait();
				}
				
				Random rand = new Random();
				int jobId = rand.nextInt(0, 100);
				printJobs.add(jobId);
				System.out.println("produced a job: " + jobId);
				
				notify();
				Thread.sleep(200);
			}
		}
	}

	public void consomeJobs() throws InterruptedException {
		for (int i = 0; i < jobCount; i++) {
			synchronized (this) {
				while (printJobs.isEmpty()) {
					wait();
				}

				int jobId = printJobs.remove(0);

				System.out.println("Finished Job: " + jobId);
				notify();
				Thread.sleep(200);
			}
		}
	}
}
