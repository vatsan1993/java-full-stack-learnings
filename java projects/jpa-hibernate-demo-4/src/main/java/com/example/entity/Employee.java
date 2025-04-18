package com.example.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;

@Entity
@Table(name = "emps4")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee implements Serializable{
	@Id
	@Column(name="eno")
	private Long empno;
	
	@Column(name="fnm", nullable = false)
	private String firstName;
	
	@Column(name="lnm", nullable = false)
	private String lastName;
	
	@Column(name="basic", nullable = false)
	private Double basic;
	
	@Column(name="doj", nullable = false)
	private LocalDate joinDate;
	
	@Embedded
	private Address address;
	
	public Employee() {
		
	}	

	public Employee(Long empno, String firstName, String lastName, Double basic, LocalDate joinDate, Address address) {
		super();
		this.empno = empno;
		this.firstName = firstName;
		this.lastName = lastName;
		this.basic = basic;
		this.joinDate = joinDate;
		this.address = address;
	}

	public Long getEmpno() {
		return empno;
	}


	public void setEmpno(Long empno) {
		this.empno = empno;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getBasic() {
		return basic;
	}

	public void setBasic(Double basic) {
		this.basic = basic;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}
	
	

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", firstName=" + firstName + ", lastName=" + lastName + ", basic=" + basic
				+ ", joinDate=" + joinDate + "\n" +address.toString() +"\n]";
	}
	

}
