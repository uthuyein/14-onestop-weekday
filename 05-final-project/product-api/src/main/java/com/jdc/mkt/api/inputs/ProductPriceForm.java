package com.jdc.mkt.api.inputs;

import java.time.LocalDate;

import com.jdc.mkt.model.entities.Product;
import com.jdc.mkt.model.entities.ProductPrice;
import com.jdc.mkt.model.entities.ProductPrice.PriceType;
import com.jdc.mkt.model.entities.Size;

import jakarta.validation.constraints.NotNull;

public record ProductPriceForm(
		@NotNull(message = "Please select category first !")
		Integer categoryId,
		@NotNull(message = "Please select product first !")
		Integer productId,
		@NotNull(message = "Please select product size !")
		Integer sizeId,
		@NotNull(message = "Please select price type !")
		PriceType priceType,
		@NotNull(message = "Please type product price !")
		Double price,
		boolean isActive,
		LocalDate createAt,
		LocalDate updateAt
	
		) {
	
	public ProductPrice entity(ProductPrice p) {
		
		p.setProduct(new Product(productId));
		p.setSize(new Size(sizeId));
		p.setPriceType(priceType);
		p.setPrice(price);		
		p.setActive(isActive);
		p.setCreateAt(createAt != null ? createAt : LocalDate.now());
		p.setCreateAt(updateAt != null ? updateAt : LocalDate.now());
		
		return p;
	}

	

}
