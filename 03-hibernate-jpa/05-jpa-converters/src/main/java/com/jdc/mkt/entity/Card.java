package com.jdc.mkt.entity;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Card implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String number;
	private LocalDate dob;
	private LocalDate expiredDt;
	
	@Override
	public String toString() {
		return number+":"+dob+":"+expiredDt;
	}
}
