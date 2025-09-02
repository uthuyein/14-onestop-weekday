package com.jdc.mkt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Account {

	private int id;
	private Customer customer;
	private double balance;
	
	
	
}
