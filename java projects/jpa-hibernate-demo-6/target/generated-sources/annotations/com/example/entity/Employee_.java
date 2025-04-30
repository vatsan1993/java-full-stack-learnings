package com.example.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String JOIN_DATE = "joinDate";
	public static final String ADDRESS = "address";
	public static final String EMPNO = "empno";
	public static final String BASIC = "basic";
	public static final String DEPARTMENT = "department";
	public static final String ACCOUNT = "account";

	
	/**
	 * @see com.example.entity.Employee#firstName
	 **/
	public static volatile SingularAttribute<Employee, String> firstName;
	
	/**
	 * @see com.example.entity.Employee#lastName
	 **/
	public static volatile SingularAttribute<Employee, String> lastName;
	
	/**
	 * @see com.example.entity.Employee#joinDate
	 **/
	public static volatile SingularAttribute<Employee, LocalDate> joinDate;
	
	/**
	 * @see com.example.entity.Employee#address
	 **/
	public static volatile SingularAttribute<Employee, Address> address;
	
	/**
	 * @see com.example.entity.Employee#empno
	 **/
	public static volatile SingularAttribute<Employee, Long> empno;
	
	/**
	 * @see com.example.entity.Employee#basic
	 **/
	public static volatile SingularAttribute<Employee, Double> basic;
	
	/**
	 * @see com.example.entity.Employee#department
	 **/
	public static volatile SingularAttribute<Employee, Department> department;
	
	/**
	 * @see com.example.entity.Employee
	 **/
	public static volatile EntityType<Employee> class_;
	
	/**
	 * @see com.example.entity.Employee#account
	 **/
	public static volatile SingularAttribute<Employee, BankAccount> account;

}

