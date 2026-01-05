package com.jdc.mkt.model.entities;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class SaleDetailPk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "sale_id",insertable = false,updatable = false)
	private UUID saleId;
	@Column(name = "sale_product_id",insertable = false,updatable = false)
	private int saleProductId;
}
