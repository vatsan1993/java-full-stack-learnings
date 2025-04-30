package com.example.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Manager.class)
public abstract class Manager_ extends com.example.entity.Employee_ {

	public static final String ALLOWANCE = "allowance";

	
	/**
	 * @see com.example.entity.Manager#allowance
	 **/
	public static volatile SingularAttribute<Manager, Double> allowance;
	
	/**
	 * @see com.example.entity.Manager
	 **/
	public static volatile EntityType<Manager> class_;

}

