package com.jdc.mkt.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "category_tbl")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false,length = 45,unique = true)
	private String name;
	
	private boolean isActive;
	
	@ManyToOne
	private Category category;
	
//	@OneToMany(mappedBy = "category",
//			cascade = {CascadeType.PERSIST,CascadeType.MERGE})
//	List<Product> products;
}
