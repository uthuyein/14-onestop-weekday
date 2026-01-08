package com.jdc.mkt.api.outputs;

import com.jdc.mkt.model.entities.Supplier;

public record SelectSupplier(
		Integer id,
		String name
		) {

	public static SelectSupplier from(Supplier s) {
		return new SelectSupplier(s.getId(), s.getName());
	}

	
}
