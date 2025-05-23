package com.example.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "baccs")
public class BankAccount implements Serializable{
	@Id
	@Column(name="acno")
	private Long accNum;
	@Column(name="branch")
	private String branch;
	@Column(name="bank")
	private String bank;
	
	@OneToOne
	@JoinColumn(name = "ach")
	private Employee accountHolder;
	
	public BankAccount() {
		
	}

	public BankAccount(Long accNum, String branch, String bank) {
		super();
		this.accNum = accNum;
		this.branch = branch;
		this.bank = bank;
		
	}

	public Long getAccNum() {
		return accNum;
	}

	public void setAccNum(Long accNum) {
		this.accNum = accNum;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Employee getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(Employee accountHolder) {
		this.accountHolder = accountHolder;
	}

	@Override
	public String toString() {
		return "BankAccount [accNum=" + accNum + ", branch=" + branch + ", bank=" + bank 
				+ "]";
	}
}
