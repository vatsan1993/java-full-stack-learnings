package com.example.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Item.class)
public abstract class Item_ {

	public static final String ICODE = "icode";
	public static final String PACKAGE_DATE = "packageDate";
	public static final String UNIT = "unit";
	public static final String SELLING_PRICE = "sellingPrice";
	public static final String COST_PRICE = "costPrice";
	public static final String QUERY_LIST_SINGLE_ITEMS = "ListSingleItems";
	public static final String FRAGILE = "fragile";
	public static final String QUERY_LIST_ITEMS_WITH_FRAGILE_FILTER = "ListItemsWithFragileFilter";
	public static final String TITLE = "title";

	
	/**
	 * @see com.example.entity.Item#icode
	 **/
	public static volatile SingularAttribute<Item, Integer> icode;
	
	/**
	 * @see com.example.entity.Item#packageDate
	 **/
	public static volatile SingularAttribute<Item, LocalDate> packageDate;
	
	/**
	 * @see com.example.entity.Item#unit
	 **/
	public static volatile SingularAttribute<Item, String> unit;
	
	/**
	 * @see com.example.entity.Item#sellingPrice
	 **/
	public static volatile SingularAttribute<Item, Integer> sellingPrice;
	
	/**
	 * @see com.example.entity.Item#costPrice
	 **/
	public static volatile SingularAttribute<Item, Integer> costPrice;
	
	/**
	 * @see com.example.entity.Item#fragile
	 **/
	public static volatile SingularAttribute<Item, Boolean> fragile;
	
	/**
	 * @see com.example.entity.Item#title
	 **/
	public static volatile SingularAttribute<Item, String> title;
	
	/**
	 * @see com.example.entity.Item
	 **/
	public static volatile EntityType<Item> class_;

}

