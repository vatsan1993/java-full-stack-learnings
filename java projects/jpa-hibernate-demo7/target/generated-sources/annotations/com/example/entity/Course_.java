package com.example.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Course.class)
public abstract class Course_ {

	public static final String NAME = "name";
	public static final String STUDENTS = "students";
	public static final String ID = "id";

	
	/**
	 * @see com.example.entity.Course#name
	 **/
	public static volatile SingularAttribute<Course, String> name;
	
	/**
	 * @see com.example.entity.Course#students
	 **/
	public static volatile SetAttribute<Course, Student> students;
	
	/**
	 * @see com.example.entity.Course#id
	 **/
	public static volatile SingularAttribute<Course, Long> id;
	
	/**
	 * @see com.example.entity.Course
	 **/
	public static volatile EntityType<Course> class_;

}

