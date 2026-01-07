package com.jdc.mkt.model.entities;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customer_tbl")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false,length = 45)
	private String name;
	
	@ColumnDefault("1")
	private boolean isActive;
	
	@Enumerated(EnumType.STRING)
	private MemberType memberType;
	
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Address address;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Contact contact;
	
	
	public enum MemberType{
		Silver,Gold,Diamond
	}
}
