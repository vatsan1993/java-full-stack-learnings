package com.wf.project.DemoProject;

import java.util.ArrayList;
import java.util.List;

public class BusReservationCounter implements Runnable {
	private Bus bus;
	private int seatsRequired;
	private List<Integer> seatsAllocated ;
	
	public BusReservationCounter(Bus bus,  int seatsRequired) {
		this.bus = bus;
		this.seatsRequired = seatsRequired;
		this.seatsAllocated = new ArrayList<Integer>();
	}
	
	public void reserve() throws InterruptedException {
		synchronized (bus) {
			while(seatsRequired > 0) {
				if(bus.getAvailableSeats( ) >= seatsRequired) {
					int availableSeat = bus.getAvailableSeats();
					seatsAllocated.add(availableSeat);
					bus.setAvailableSeats(availableSeat - 1);
					seatsRequired --;
				} else {
					System.out.println("No seats available");
					break;
				}
				Thread.sleep(1200);
			}
			System.out.println("Thread "+ Thread.currentThread().getName() + ": Alloted Seats: "+ seatsAllocated.toString());
		}
		
	}

	@Override
	public void run() {
		
		try {
			reserve();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}
