package com.jdc.mkt.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
@Embeddable
public class VoucherDetailPk implements Serializable{

	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@ManyToOne
//	private Product product;
//	@ManyToOne
//	private Voucher voucher;
	
	@Column(name = "voucher_id",insertable = false,updatable = false)
	private int voucherId;
	@Column(name = "product_id",insertable = false,updatable = false)
	private int productId;
}
