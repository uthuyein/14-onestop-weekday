package com.jdc.mkt.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.LocalTime;

@StaticMetamodel(Voucher.class)
public abstract class Voucher_ {

	public static final String SALE_TIME = "saleTime";
	public static final String ID = "id";
	public static final String SALE_DATE = "saleDate";
	public static final String VOUCHER_DETAILS = "voucherDetails";
	public static final String CUSTOMER = "customer";

	
	/**
	 * @see com.jdc.mkt.entity.Voucher#saleTime
	 **/
	public static volatile SingularAttribute<Voucher, LocalTime> saleTime;
	
	/**
	 * @see com.jdc.mkt.entity.Voucher#id
	 **/
	public static volatile SingularAttribute<Voucher, Integer> id;
	
	/**
	 * @see com.jdc.mkt.entity.Voucher#saleDate
	 **/
	public static volatile SingularAttribute<Voucher, LocalDate> saleDate;
	
	/**
	 * @see com.jdc.mkt.entity.Voucher
	 **/
	public static volatile EntityType<Voucher> class_;
	
	/**
	 * @see com.jdc.mkt.entity.Voucher#voucherDetails
	 **/
	public static volatile ListAttribute<Voucher, VoucherDetail> voucherDetails;
	
	/**
	 * @see com.jdc.mkt.entity.Voucher#customer
	 **/
	public static volatile SingularAttribute<Voucher, Customer> customer;

}

