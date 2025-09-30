package com.jdc.mkt.entity.dto;

import com.jdc.mkt.entity.Customer.MemberType;

public record SelectCuNameCuTypeAndEmail(
		String customer,
		MemberType memberType,
		String email
		) {

}
