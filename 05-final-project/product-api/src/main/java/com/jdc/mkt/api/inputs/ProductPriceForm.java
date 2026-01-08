package com.jdc.mkt.api.inputs;

import java.time.LocalDate;

import com.jdc.mkt.model.entities.Product;
import com.jdc.mkt.model.entities.ProductPrice;
import com.jdc.mkt.model.entities.ProductPrice.PriceType;
import com.jdc.mkt.model.entities.Size;

import jakarta.validation.constraints.NotNull;

public record ProductPriceForm(
		@NotNull(message = "Please select product first !")
		Product product,
		@NotNull(message = "Please select product size !")
		Size size,
		@NotNull(message = "Please select price type !")
		PriceType priceType,
		@NotNull(message = "Please type product price !")
		Double price
		) {
	
	public ProductPrice entity(ProductPrice p) {
		p.setProduct(product);
		p.setSize(size);
		p.setPriceType(priceType);
		p.setPrice(price);
		p.setCreateAt(LocalDate.now());
		
		if(null != p.getId()) {
		p.setUpdateAt(LocalDate.now());
		}
		return p;
	}

	

}
