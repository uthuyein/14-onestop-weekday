package com.jdc.mkt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.SecondaryTables;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "product_tbl")
@SecondaryTables({
	@SecondaryTable(name = "product_size_tbl")
})
public class Product {

	@Id
	private int id;
	private String name;
	private Double price;
	private Boolean active;
		
	@Enumerated(EnumType.STRING)
	@Column(table = "product_size_tbl")
	private Size size;
	
	public enum Size{
		Small,Medium,Large
	}
}
