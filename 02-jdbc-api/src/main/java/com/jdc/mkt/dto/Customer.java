package com.jdc.mkt.dto;

public class Customer {

	private int id;
	private String name;
	private MemberType memberType;
	private boolean active;
		
	public Customer(int id, String name,MemberType memberType) {
		super();
		this.id = id;
		this.name = name;
		this.memberType = memberType;
	}

	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	
	public MemberType getMemberType() {
		return memberType;
	}

	public boolean isActive() {
		return active;
	}



	public enum MemberType{
		Silver,Gold,Diamond,Platinum
	}
	
}
