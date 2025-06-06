package com.example.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="emps_c_only")
public class ContractEmployee extends Employee {
	
	@Column(name="cdur")
	private Integer contractDuration;

	public ContractEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContractEmployee(Long empno, String firstName, String lastName, Double basic, LocalDate joinDate, Integer contractDuration) {
		super(empno, firstName, lastName, basic, joinDate);
		this.contractDuration = contractDuration;
	}

	public Integer getContractDuration() {
		return contractDuration;
	}

	public void setContractDuration(Integer contractDuration) {
		this.contractDuration = contractDuration;
	}

	@Override
	public String toString() {
		return super.toString() + " ContractEmployee [contractDuration=" + contractDuration + "]";
	}

}
