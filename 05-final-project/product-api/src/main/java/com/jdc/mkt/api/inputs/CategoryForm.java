package com.jdc.mkt.api.inputs;

import com.jdc.mkt.model.entities.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryForm(
		@NotBlank(message = "Please type category name !") 
		String name,
		@NotNull(message = "Please type category id !")
		Integer categoryId,
		Boolean isActive) {

	public Category entity() {
		var cat = new Category();
		cat.setName(name);
		cat.setActive(isActive);
		
		var catId = new Category();
		
		if (null != categoryId) {
			catId.setId(categoryId);
			
			cat.setCategory(catId);
		}
		return cat;
	}

}
