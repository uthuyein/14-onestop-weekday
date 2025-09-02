package com.jdc.mkt.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Customer {

	private final int id;
	private final String name;
	private final MemberType memberType;
	private boolean active;
		
	public enum MemberType{
		Silver,Gold,Diamond,Platinum
	}
	
}

