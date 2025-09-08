package com.jdc.mkt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.SecondaryTables;
import jakarta.persistence.SequenceGenerator;
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
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@GeneratedValue(generator = "seq_gen_customer_tbl")
	//@TableGenerator(name = "table_gen_customer_tbl",initialValue = 5,allocationSize = 1)
	@SequenceGenerator(name = "seq_gen_customer_tbl",initialValue = 1,allocationSize = 1)
	private int id;
	private String name;
	
	@Embedded
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
