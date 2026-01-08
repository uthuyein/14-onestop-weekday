package com.jdc.mkt.api.inputs;

import com.jdc.mkt.model.entities.Supplier;

public record SupplierForm (
		String name
		){

	public Supplier entity(Supplier supplier) {
		supplier.setName(name);
		return supplier;
	}

}
