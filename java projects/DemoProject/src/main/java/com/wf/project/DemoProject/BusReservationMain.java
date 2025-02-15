package com.wf.project.DemoProject;

public class BusReservationMain {
	public static void main(String[] args) {
		Bus b1 = new Bus(25);
		BusReservationCounter counter1 = new BusReservationCounter(b1, 10);
		BusReservationCounter counter2 = new BusReservationCounter(b1, 10);
		BusReservationCounter counter3 = new BusReservationCounter(b1, 10);
		
		Thread t1 = new Thread(counter1);
		Thread t2 = new Thread(counter2);
		Thread t3 = new Thread(counter3);
		
		t1.start();
		t2.start();
		t3.start();
	}
}
