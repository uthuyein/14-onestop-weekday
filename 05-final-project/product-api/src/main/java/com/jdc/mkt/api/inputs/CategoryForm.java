package com.jdc.mkt.api.inputs;

import com.jdc.mkt.model.entities.Category;

import jakarta.validation.constraints.NotBlank;

public record CategoryForm(
		Integer id,
		@NotBlank(message = "Please type category name !") 
		String name,
		Integer subCategoryId,
		boolean isActive
		) {

	public Category entity(Category cat) {
		cat.setId(id);
		cat.setName(name);
		cat.setActive(isActive);
		
		if (null != subCategoryId) {			
			var catId = new Category();
			catId.setId(subCategoryId);
			cat.setCategory(catId);
		}
		return cat;
	}

}
