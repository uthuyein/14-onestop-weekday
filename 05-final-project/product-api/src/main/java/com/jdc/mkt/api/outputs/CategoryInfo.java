package com.jdc.mkt.api.outputs;

import com.jdc.mkt.model.entities.Category;

public record CategoryInfo(
		int id,
		String name,
		Category category
		)implements Info {

	public static CategoryInfo from(Category c) {
		return new CategoryInfo(c.getId(),c.getName(),c.getCategory());
	}

	@Override
	public Integer getId() {
		return id;
	}

	

}
