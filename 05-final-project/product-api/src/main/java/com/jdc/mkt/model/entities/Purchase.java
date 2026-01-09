package com.jdc.mkt.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

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
@Table(name = "purchase_tbl")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDate issueDate;
	
	@ColumnDefault("1")
	private boolean isActive;
	
	@ManyToOne
	private Supplier supplier;
	
	@OneToMany(mappedBy = "purchase")
	private List<PurchaseDetail> purchaseDetails ;
	
	public Purchase() {
		purchaseDetails  = new ArrayList<PurchaseDetail>();
	}
	
	public void addPurchaseDetail(PurchaseDetail detail) {
		detail.setPurchase(this);
		purchaseDetails.add(detail);
	}
}
