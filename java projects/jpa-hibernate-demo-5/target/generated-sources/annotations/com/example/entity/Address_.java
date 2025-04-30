package com.example.entity;

import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static final String DOOR_NUMBER = "doorNumber";
	public static final String CITY = "city";
	public static final String STREET = "street";

	
	/**
	 * @see com.example.entity.Address#doorNumber
	 **/
	public static volatile SingularAttribute<Address, String> doorNumber;
	
	/**
	 * @see com.example.entity.Address#city
	 **/
	public static volatile SingularAttribute<Address, String> city;
	
	/**
	 * @see com.example.entity.Address#street
	 **/
	public static volatile SingularAttribute<Address, String> street;
	
	/**
	 * @see com.example.entity.Address
	 **/
	public static volatile EmbeddableType<Address> class_;

}

