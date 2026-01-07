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
		Integer catId,
		Boolean isActive
		) {

	public Product entity(Integer id) {
		var p = new Product();
		p.setId(id);
		p.setName(name);
		
		var c = new Category();
		c.setId(catId);
		p.setCategory(c);
		
		return p;
	}

	

}
