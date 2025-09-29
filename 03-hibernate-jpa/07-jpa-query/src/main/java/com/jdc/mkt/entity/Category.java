package com.jdc.mkt.entity;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "category_tbl")
@NamedQuery(
		name = "selectAllCategory",
		query = "select c from Category c")
@NamedNativeQuery(
		name = "selectAllCategoryWithSql",
		query = "select * from category_tbl",
		resultClass = Category.class		
		)
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,unique = true,length = 45)
	private String name;
	
	@ColumnDefault("true")
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name = "subCategory_id",nullable = true )
	private Category category;
	
	@OneToMany(mappedBy = "category")
	private List<Product> products;
}



