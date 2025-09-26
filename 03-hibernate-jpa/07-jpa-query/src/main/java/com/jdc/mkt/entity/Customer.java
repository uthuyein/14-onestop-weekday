package com.jdc.mkt.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customer_tbl")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,unique = true,length = 45)
	private String name;
	
	@ColumnDefault("true")
	private boolean active;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "member_type")
	private MemberType memberType;
	
	@OneToOne(mappedBy = "customer")
	private Contact contact;
	
	public enum MemberType{
		NoMember,Silver,Gold,Platinum,Diamond
	}
}
