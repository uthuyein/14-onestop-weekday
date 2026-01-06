package com.jdc.mkt.utils;

public record ModificationResult<ID>(
		ID id,
		boolean success,
		String message
		) {

	public static<ID> ModificationResult<ID> success(ID id,String message){		
		return new ModificationResult<ID>(id, true,message);
	}
	
}
