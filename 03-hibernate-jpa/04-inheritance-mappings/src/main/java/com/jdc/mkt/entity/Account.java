package com.jdc.mkt.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("A")
@DiscriminatorColumn(name = "Child",discriminatorType = DiscriminatorType.CHAR)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@MappedSuperclass
public abstract class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id ;
	private String name;
	private String loginId;
	private String password;
}
