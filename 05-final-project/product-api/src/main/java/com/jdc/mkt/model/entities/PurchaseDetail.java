package com.jdc.mkt.model.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "purchase_detail_tbl")
public class PurchaseDetail {

	@EmbeddedId
	private PurchaseDetailPk id;
	
	private int qty;
	
	@ManyToOne
	@JoinColumn(insertable = false,updatable = false)
	private Purchase purchase;
	
	@ManyToOne
	@JoinColumn(insertable = false,updatable = false)
	private ProductPrice productPrice;
	
	
	
}
