package com.jdc.mkt.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Category.class)
public abstract class Category_ {

	public static final String QUERY_SELECT_ALL_CATEGORY_WITH_SQL = "selectAllCategoryWithSql";
	public static final String QUERY_SELECT_ALL_CATEGORY = "selectAllCategory";
	public static final String NAME = "name";
	public static final String ACTIVE = "active";
	public static final String ID = "id";
	public static final String CATEGORY = "category";
	public static final String PRODUCTS = "products";

	
	/**
	 * @see com.jdc.mkt.entity.Category#name
	 **/
	public static volatile SingularAttribute<Category, String> name;
	
	/**
	 * @see com.jdc.mkt.entity.Category#active
	 **/
	public static volatile SingularAttribute<Category, Boolean> active;
	
	/**
	 * @see com.jdc.mkt.entity.Category#id
	 **/
	public static volatile SingularAttribute<Category, Integer> id;
	
	/**
	 * @see com.jdc.mkt.entity.Category#category
	 **/
	public static volatile SingularAttribute<Category, Category> category;
	
	/**
	 * @see com.jdc.mkt.entity.Category
	 **/
	public static volatile EntityType<Category> class_;
	
	/**
	 * @see com.jdc.mkt.entity.Category#products
	 **/
	public static volatile ListAttribute<Category, Product> products;

}

