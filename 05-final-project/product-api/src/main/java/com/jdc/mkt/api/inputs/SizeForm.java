package com.jdc.mkt.api.inputs;

import com.jdc.mkt.model.entities.Size;

public record SizeForm(
		String name
		) {

	public Size entity(Size size) {
		size.setName(name);
		return size;
	}

}
