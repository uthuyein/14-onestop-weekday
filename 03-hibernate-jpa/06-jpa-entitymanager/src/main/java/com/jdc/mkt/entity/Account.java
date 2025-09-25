package com.jdc.mkt.entity;

import com.jdc.mkt.listener.EnableTimesListener;
import com.jdc.mkt.listener.Times;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "account_tbl")
public class Account implements EnableTimesListener{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,length = 45)
	private String name;
	
	private double balance;
	
	@Embedded
	private Times times;
}
