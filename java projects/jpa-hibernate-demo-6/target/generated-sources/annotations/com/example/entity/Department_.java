package com.example.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Department.class)
public abstract class Department_ {

	public static final String DEPT_NAME = "deptName";
	public static final String DEPARTMENT_NO = "departmentNo";
	public static final String EMPLOYEES = "employees";

	
	/**
	 * @see com.example.entity.Department#deptName
	 **/
	public static volatile SingularAttribute<Department, String> deptName;
	
	/**
	 * @see com.example.entity.Department#departmentNo
	 **/
	public static volatile SingularAttribute<Department, Long> departmentNo;
	
	/**
	 * @see com.example.entity.Department#employees
	 **/
	public static volatile SetAttribute<Department, Employee> employees;
	
	/**
	 * @see com.example.entity.Department
	 **/
	public static volatile EntityType<Department> class_;

}

