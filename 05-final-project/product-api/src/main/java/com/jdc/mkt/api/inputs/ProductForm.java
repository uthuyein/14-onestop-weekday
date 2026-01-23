package com.jdc.mkt.api.inputs;


import com.jdc.mkt.model.entities.Category;
import com.jdc.mkt.model.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductForm(
		Integer id,
		@NotBlank(message = "Please type product name !")
		String name,
		@NotNull(message = "Please select category !")
		Integer categoryId,
		Boolean isActive
		) {

	public Product entity(Product p) {
		var c = new Category();
		c.setId(categoryId);
		p.setName(name);
		
		p.setCategory(c);
		p.setActive(isActive);
		return p;
	}

}
