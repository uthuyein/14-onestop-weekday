package com.jdc.mkt.utils;

public record StatusMessage<ID>(
		ID id,
		boolean success,
		String message
		) {

	public static<ID> StatusMessage<ID> success(ID id,String message){
		
		return new StatusMessage<ID>(id, true,message);
	}
	
}
