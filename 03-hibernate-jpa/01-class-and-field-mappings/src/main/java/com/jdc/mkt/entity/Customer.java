package com.jdc.mkt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.SecondaryTables;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customer_tbl")
@SecondaryTables({
	@SecondaryTable(name = "contact_tbl"),
	@SecondaryTable(name = "address_tbl")
})
public class Customer {

	@Id
	private int id;
	private String name;
	private Account account;
	
	@Column(table = "contact_tbl")
	private String email;	
	@Column(table = "contact_tbl")
	private String phone;
	
	@Column(table = "address_tbl")
	private String city;
	@Column(table = "address_tbl")
	private String township;
	@Column(table = "address_tbl")
	private String street;
}
