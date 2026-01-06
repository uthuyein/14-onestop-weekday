package com.jdc.mkt.model.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "purchase_detail_tbl")
public class PurchaseDetail {

	@EmbeddedId
	private PurchaseDetailPk id;
	
	@ManyToOne
	private Purchase purchase;
	
	@ManyToOne
	private ProductPrice productPrice;
	
	private double price;
	private int qty;
	
	
}
