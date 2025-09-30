package com.jdc.mkt.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(VoucherDetail.class)
public abstract class VoucherDetail_ {

	public static final String PRODUCT = "product";
	public static final String VOUCHER = "voucher";
	public static final String QTY = "qty";
	public static final String ID = "id";
	public static final String SUB_TOTAL = "subTotal";

	
	/**
	 * @see com.jdc.mkt.entity.VoucherDetail#product
	 **/
	public static volatile SingularAttribute<VoucherDetail, Product> product;
	
	/**
	 * @see com.jdc.mkt.entity.VoucherDetail#voucher
	 **/
	public static volatile SingularAttribute<VoucherDetail, Voucher> voucher;
	
	/**
	 * @see com.jdc.mkt.entity.VoucherDetail#qty
	 **/
	public static volatile SingularAttribute<VoucherDetail, Integer> qty;
	
	/**
	 * @see com.jdc.mkt.entity.VoucherDetail#id
	 **/
	public static volatile SingularAttribute<VoucherDetail, VoucherDetailPk> id;
	
	/**
	 * @see com.jdc.mkt.entity.VoucherDetail#subTotal
	 **/
	public static volatile SingularAttribute<VoucherDetail, Double> subTotal;
	
	/**
	 * @see com.jdc.mkt.entity.VoucherDetail
	 **/
	public static volatile EntityType<VoucherDetail> class_;

}

