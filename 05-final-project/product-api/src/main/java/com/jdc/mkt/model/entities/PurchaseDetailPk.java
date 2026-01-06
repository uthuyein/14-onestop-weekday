package com.jdc.mkt.model.entities;

import java.io.Serializable;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PurchaseDetailPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private LocalTime purchaseTime;
	
	@Column(name = "purchase_id",insertable = false,updatable = false)
	private int purchaseId;
	
	@Column(name = "product_id",insertable = false,updatable = false)
	private int productId;
	
	@Column(name = "size_id",insertable = false,updatable = false)
	private int sizeId;
	
}
