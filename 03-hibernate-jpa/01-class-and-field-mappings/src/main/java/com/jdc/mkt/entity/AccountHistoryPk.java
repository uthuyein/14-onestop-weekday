package com.jdc.mkt.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
//@Embeddable
public class AccountHistoryPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
}
