package com.jdc.mkt.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static final String WS_PRICE = "wsPrice";
	public static final String DT_PRICE = "dtPrice";
	public static final String NAME = "name";
	public static final String ACTIVE = "active";
	public static final String ID = "id";
	public static final String CATEGORY = "category";
	public static final String MAPPING_SELECT_CNAME_PNAME_WITH_COUNT = "selectCNamePNameWithCount";

	
	/**
	 * @see com.jdc.mkt.entity.Product#wsPrice
	 **/
	public static volatile SingularAttribute<Product, Double> wsPrice;
	
	/**
	 * @see com.jdc.mkt.entity.Product#dtPrice
	 **/
	public static volatile SingularAttribute<Product, Double> dtPrice;
	
	/**
	 * @see com.jdc.mkt.entity.Product#name
	 **/
	public static volatile SingularAttribute<Product, String> name;
	
	/**
	 * @see com.jdc.mkt.entity.Product#active
	 **/
	public static volatile SingularAttribute<Product, Boolean> active;
	
	/**
	 * @see com.jdc.mkt.entity.Product#id
	 **/
	public static volatile SingularAttribute<Product, Integer> id;
	
	/**
	 * @see com.jdc.mkt.entity.Product#category
	 **/
	public static volatile SingularAttribute<Product, Category> category;
	
	/**
	 * @see com.jdc.mkt.entity.Product
	 **/
	public static volatile EntityType<Product> class_;

}

