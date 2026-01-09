package com.jdc.mkt.model.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PurchaseDetailPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "purchase_id")
	private int purchaseId;
	
	@Column(name = "product_price_id")
	private int productPriceId;
	
	
}
