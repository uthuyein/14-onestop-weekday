package com.jdc.mkt.model.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product_price_tbl")
public class ProductPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Size size;
	
	@Enumerated(EnumType.STRING)
	private PriceType priceType;
	
	private Double price;
	
	private LocalDate createAt;
	private LocalDate updateAt;
	
	public enum PriceType{
		Purchase,Sale
	}
}
