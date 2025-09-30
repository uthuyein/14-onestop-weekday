package com.jdc.mkt.entity;

import com.jdc.mkt.entity.Customer.MemberType;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static final String MAPPING_SELECT_MEMBER_COUNT = "selectMemberCount";
	public static final String CONTACT = "contact";
	public static final String NAME = "name";
	public static final String ACTIVE = "active";
	public static final String ID = "id";
	public static final String MEMBER_TYPE = "memberType";
	public static final String QUERY_SELECT_MEMBER_AND_COUNT = "selectMemberAndCount";

	
	/**
	 * @see com.jdc.mkt.entity.Customer#contact
	 **/
	public static volatile SingularAttribute<Customer, Contact> contact;
	
	/**
	 * @see com.jdc.mkt.entity.Customer#name
	 **/
	public static volatile SingularAttribute<Customer, String> name;
	
	/**
	 * @see com.jdc.mkt.entity.Customer#active
	 **/
	public static volatile SingularAttribute<Customer, Boolean> active;
	
	/**
	 * @see com.jdc.mkt.entity.Customer#id
	 **/
	public static volatile SingularAttribute<Customer, Integer> id;
	
	/**
	 * @see com.jdc.mkt.entity.Customer#memberType
	 **/
	public static volatile SingularAttribute<Customer, MemberType> memberType;
	
	/**
	 * @see com.jdc.mkt.entity.Customer
	 **/
	public static volatile EntityType<Customer> class_;

}

