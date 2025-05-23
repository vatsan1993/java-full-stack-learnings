package com.example.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="depts")
public class Department implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="deptno")
	private Long departmentNo;
	
	@Column(name = "dname")
	private String deptName;
	
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Employee> employees;
	
	public Department() {
		
	}

	public Department(Long departmentNo, String deptName) {
		super();
		this.departmentNo = departmentNo;
		this.deptName = deptName;
		this.employees = new TreeSet<Employee>();
	}
	
	public void addEmployee(Employee emp) {
		employees.add(emp);
	}

	public Long getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(Long departmentNo) {
		this.departmentNo = departmentNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Department [departmentNo=" + departmentNo + ", deptName=" + deptName + "]";
	}
	
	
}
