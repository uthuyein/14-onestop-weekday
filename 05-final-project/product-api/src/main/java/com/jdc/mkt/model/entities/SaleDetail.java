package com.jdc.mkt.model.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sale_detail_tbl")
public class SaleDetail {

	@EmbeddedId
	private SaleDetailPk id;
	
	private int qty;
	private double subTotal;

	@ManyToOne
	private Sale sale;
	
	@ManyToOne
	private ProductPrice productPrice;
	
	
}
