package com.jdc.mkt.api.outputs;

import com.jdc.mkt.model.entities.Category;

public record SelectCategory(
		int id,
		String name,
		Category subCategory
		){

	public static SelectCategory from(Category c) {
		return new SelectCategory(c.getId(),c.getName(),c.getCategory());
	}

}
