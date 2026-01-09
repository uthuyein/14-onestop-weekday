package com.jdc.mkt.utils;

public record ModificationResult<ID>(
		ID id,
		UpdateStatus status,
		String message
		) {

	public static<ID> ModificationResult<ID> success(ID id,UpdateStatus status,String name){		
		return new ModificationResult<ID>(id, status,"%s has successfully %s".formatted(name,status));
	}
	
	public enum UpdateStatus{
		Save,Update
	}
	
}
