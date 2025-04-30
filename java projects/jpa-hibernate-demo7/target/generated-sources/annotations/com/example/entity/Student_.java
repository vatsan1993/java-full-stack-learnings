package com.example.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Student.class)
public abstract class Student_ {

	public static final String COURSES = "courses";
	public static final String NAME = "name";
	public static final String ID = "id";

	
	/**
	 * @see com.example.entity.Student#courses
	 **/
	public static volatile SetAttribute<Student, Course> courses;
	
	/**
	 * @see com.example.entity.Student#name
	 **/
	public static volatile SingularAttribute<Student, String> name;
	
	/**
	 * @see com.example.entity.Student#id
	 **/
	public static volatile SingularAttribute<Student, Long> id;
	
	/**
	 * @see com.example.entity.Student
	 **/
	public static volatile EntityType<Student> class_;

}

