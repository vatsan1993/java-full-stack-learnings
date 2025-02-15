package com.example.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="emps_m_only")
public class Manager extends Employee{
	@Column(name = "alwnc")
	private Double allowance;

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(Long empno, String firstName, String lastName, Double basic, LocalDate joinDate, Double allowance) {
		super(empno, firstName, lastName, basic, joinDate);
		this.allowance = allowance;
	}

	public Double getAllowance() {
		return allowance;
	}

	public void setAllowance(Double allowance) {
		this.allowance = allowance;
	}

	@Override
	public String toString() {
		return super.toString() + " Manager [allowance=" + allowance + "]";
	}

}
