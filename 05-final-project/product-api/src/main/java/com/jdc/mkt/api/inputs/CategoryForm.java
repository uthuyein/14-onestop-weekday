package com.jdc.mkt.api.inputs;

import com.jdc.mkt.model.entities.Category;

import jakarta.validation.constraints.NotBlank;

public record CategoryForm(
		@NotBlank(message = "Please type category name !") 
		String name,
		Integer categoryId,
		Boolean isActive) {

	public Category entity() {
		var cat = new Category();
		cat.setName(name);
		cat.setActive(isActive);

		if (null != categoryId) {
			
			var catId = new Category();
			catId.setId(categoryId);
			cat.setCategory(catId);
		}
		return cat;
	}

}
