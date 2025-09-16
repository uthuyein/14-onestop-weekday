package com.jdc.mkt.entity;

import org.hibernate.annotations.Check;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product_tbl")
@Check(constraints = "dt_price >= ws_price")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,length = 45)
	private String name;
	
	@Column(name = "dt_price")
	private double dtPrice;
	@Column(name = "ws_price")
	private double wsPrice;
	
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean active;
	
	@ManyToOne(fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	private Category category;
}
