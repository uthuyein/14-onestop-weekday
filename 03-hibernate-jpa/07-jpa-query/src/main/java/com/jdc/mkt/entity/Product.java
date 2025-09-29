package com.jdc.mkt.entity;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import com.jdc.mkt.entity.dto.SelectCNamePNameWithCount;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product_tbl")
@Check(constraints = "dt_price >= ws_price")
@SqlResultSetMapping(
		name = "selectCNamePNameWithCount",
		classes = {
		@ConstructorResult(
			targetClass = SelectCNamePNameWithCount.class,
			columns = {
				@ColumnResult(name = "category"),
				@ColumnResult(name = "product"),
				@ColumnResult(name = "qty")
			})
		})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,unique = true,length = 45)
	private String name;
	
	@Column(name = "dt_price")
	private double dtPrice;
	
	@Column(name = "ws_price")
	private double wsPrice;
	
	@ColumnDefault("true")
	private boolean active;
	
	@ManyToOne
	private Category category;
}
