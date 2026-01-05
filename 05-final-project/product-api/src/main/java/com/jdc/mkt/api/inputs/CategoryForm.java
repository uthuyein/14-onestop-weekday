package com.jdc.mkt.api.inputs;

import com.jdc.mkt.model.entities.Category;

import jakarta.validation.constraints.NotBlank;

public record CategoryForm(
		@NotBlank(message = "Please type category name !") 
		String name,
		Integer categoryId) {

	public Category entity() {
		var cat = new Category();
		cat.setName(name);
		
		var catId = new Category();
		
		if (null != categoryId) {
			catId.setId(categoryId);
			cat.setCategory(catId);
		}
		return cat;
	}

}
