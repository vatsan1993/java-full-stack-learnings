package com.example.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ContractEmployee.class)
public abstract class ContractEmployee_ extends com.example.entity.Employee_ {

	public static final String CONTRACT_DURATION = "contractDuration";

	
	/**
	 * @see com.example.entity.ContractEmployee#contractDuration
	 **/
	public static volatile SingularAttribute<ContractEmployee, Integer> contractDuration;
	
	/**
	 * @see com.example.entity.ContractEmployee
	 **/
	public static volatile EntityType<ContractEmployee> class_;

}

