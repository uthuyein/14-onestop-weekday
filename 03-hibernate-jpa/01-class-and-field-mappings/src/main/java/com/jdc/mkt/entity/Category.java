package com.jdc.mkt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table( name = "category_tbl",
		indexes = {
			@Index(columnList = "name")	
		},
		uniqueConstraints = {
			@UniqueConstraint(columnNames = "name")
		})
public class Category {

	@Id
	private int id;
	private String name;
	private Boolean active;
}