package com.jdc.mkt.entity;

import java.util.List;

import com.jdc.mkt.listener.EnableTimesListener;
import com.jdc.mkt.listener.Times;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ExcludeDefaultListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "category_tbl")
//@EntityListeners(TimesListener.class)
@ExcludeDefaultListeners
public class Category implements EnableTimesListener{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,length = 45,unique = true)
	private String name;
	
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean active;
	
	@Embedded
	private Times times;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	@OneToMany(mappedBy = "category",
			fetch = FetchType.EAGER
			,orphanRemoval = true)
	private List<Product> products;
	
	
	
//	@PrePersist
//	void beforeSaving() {
//		System.out.println("Before saving ");
//		System.out.println("===============================");
//		
//	}
//	
//	@PostUpdate
//	void afterUpdating() {
//		System.out.println("===============================");
//		System.out.println("After updating category");
//	}
//	
//	@PreRemove
//	void beforeRemove() {
//		System.out.println("===============================");
//		System.out.println("Before remove category");
//	}
}
