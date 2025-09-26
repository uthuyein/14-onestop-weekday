package com.jdc.mkt.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "voucher_tbl")
public class Voucher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition = "date default(current_date)")
	private LocalDate saleDate;
	@Column(columnDefinition = "date default(current_time)")
	private LocalTime saleTime;
	
	@ManyToOne
	private Customer customer;
	
	@OneToMany(orphanRemoval = true,cascade = CascadeType.ALL)
	private List<VoucherDetail> voucherDetails;
}
