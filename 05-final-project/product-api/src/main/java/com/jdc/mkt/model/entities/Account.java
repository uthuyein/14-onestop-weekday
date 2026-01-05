package com.jdc.mkt.model.entities;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "account_tbl")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String loginId;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	@ColumnDefault("1")
	private boolean isActive;
	
	public enum AccountType{
		Admin,Owner,Cashier
	}
	
}
