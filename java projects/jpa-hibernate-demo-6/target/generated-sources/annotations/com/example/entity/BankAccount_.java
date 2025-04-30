package com.example.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(BankAccount.class)
public abstract class BankAccount_ {

	public static final String ACCOUNT_HOLDER = "accountHolder";
	public static final String ACC_NUM = "accNum";
	public static final String BANK = "bank";
	public static final String BRANCH = "branch";

	
	/**
	 * @see com.example.entity.BankAccount#accountHolder
	 **/
	public static volatile SingularAttribute<BankAccount, Employee> accountHolder;
	
	/**
	 * @see com.example.entity.BankAccount#accNum
	 **/
	public static volatile SingularAttribute<BankAccount, Long> accNum;
	
	/**
	 * @see com.example.entity.BankAccount#bank
	 **/
	public static volatile SingularAttribute<BankAccount, String> bank;
	
	/**
	 * @see com.example.entity.BankAccount
	 **/
	public static volatile EntityType<BankAccount> class_;
	
	/**
	 * @see com.example.entity.BankAccount#branch
	 **/
	public static volatile SingularAttribute<BankAccount, String> branch;

}

