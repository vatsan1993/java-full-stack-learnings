package com.example.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;

@Entity
@Table(name = "emps6")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee implements Serializable, Comparable<Employee>{
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
	
	@OneToOne(mappedBy = "accountHolder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private BankAccount account;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="dept")
	private Department department;
	
	
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
		this.account = account;
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
	
	

	public BankAccount getAccount() {
		return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", firstName=" + firstName + ", lastName=" + lastName + ", basic=" + basic
				+ ", joinDate=" + joinDate + "\n" +address.toString() +"\n"+account.toString()+"]";
	}

	@Override
	public int compareTo(Employee other) {
		// TODO Auto-generated method stub
		return empno.compareTo(other.empno);
	}

}
