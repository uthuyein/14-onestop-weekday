package com.jdc.mkt.model.entities;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sale_tbl")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private LocalDate saleDate;
	@ManyToOne
	private Account account;
	@ManyToOne
	private Customer customer;
	
	@ColumnDefault("1")
	private boolean isActive;
}
