package com.jdc.mkt.entity;

import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(VoucherDetailPk.class)
public abstract class VoucherDetailPk_ {

	public static final String PRODUCT_ID = "productId";
	public static final String VOUCHER_ID = "voucherId";
	public static final String ID = "id";

	
	/**
	 * @see com.jdc.mkt.entity.VoucherDetailPk#productId
	 **/
	public static volatile SingularAttribute<VoucherDetailPk, Integer> productId;
	
	/**
	 * @see com.jdc.mkt.entity.VoucherDetailPk#voucherId
	 **/
	public static volatile SingularAttribute<VoucherDetailPk, Integer> voucherId;
	
	/**
	 * @see com.jdc.mkt.entity.VoucherDetailPk#id
	 **/
	public static volatile SingularAttribute<VoucherDetailPk, Integer> id;
	
	/**
	 * @see com.jdc.mkt.entity.VoucherDetailPk
	 **/
	public static volatile EmbeddableType<VoucherDetailPk> class_;

}

