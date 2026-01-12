package com.jdc.mkt.utils;

public record ModificationResult<ID>(
		ID id,
		ModifiedType status,
		String message
		) {

	public static<ID> ModificationResult<ID> success(ID id,ModifiedType status,String name){		
		return new ModificationResult<ID>(id, status,"%s has %s to %s".
				formatted(
				name  == null ? "Operation" : status.name().toLowerCase(),
				id == null ? UpdateStatus.Fail :UpdateStatus.Successfully,
				status
				));
	}
	

	public enum ModifiedType{
		Save,Update
	}
	
	public enum UpdateStatus{
		Successfully,Fail
	}
	
	
}
