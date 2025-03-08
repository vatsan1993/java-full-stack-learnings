package com.example.model;

public class Loan {
	private double principal;
	private double interestRate;
	private double time;
	private double simpleInterest;
	private double payableAmount;
	
	public Loan() {
		super();
	}
	public Loan(double principal, double interestRate, double time, double simpleInterest, double payableAmount) {
		super();
		this.principal = principal;
		this.interestRate = interestRate;
		this.time = time;
		this.simpleInterest = simpleInterest;
		this.payableAmount = payableAmount;
	}
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public double getSimpleInterest() {
		return simpleInterest;
	}
	public void setSimpleInterest(double simpleInterest) {
		this.simpleInterest = simpleInterest;
	}
	public double getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(double payableAmount) {
		this.payableAmount = payableAmount;
	}
	
	
}
