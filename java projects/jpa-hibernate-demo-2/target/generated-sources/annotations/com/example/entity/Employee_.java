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
	public static final String QUERY_LIST_EMPS_WITH_LAST_NAME = "listEmpsWithLastName";
	public static final String EMPNO = "empno";
	public static final String BASIC = "basic";
	public static final String QUERY_LIST_EMPS_IN_BASIC_RANGE = "listEmpsInBasicRange";

	
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
	 * @see com.example.entity.Employee#empno
	 **/
	public static volatile SingularAttribute<Employee, Long> empno;
	
	/**
	 * @see com.example.entity.Employee#basic
	 **/
	public static volatile SingularAttribute<Employee, Double> basic;
	
	/**
	 * @see com.example.entity.Employee
	 **/
	public static volatile EntityType<Employee> class_;

}

