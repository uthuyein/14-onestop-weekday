package com.jdc.mkt.api.inputs;

import java.time.LocalDate;
import java.util.List;

import com.jdc.mkt.model.entities.Purchase;
import com.jdc.mkt.model.entities.Supplier;

import jakarta.validation.constraints.NotNull;

public record PurchaseForm(
		LocalDate issueDate,
		@NotNull(message = "Please select supplier !")
		Supplier supplier,
		@NotNull(message = "Please create atleast one product purchase !")
		List<PurchaseDetailForm> purchaseDetails
		) {

	public Purchase entity(Purchase purchase) {
		purchase.setIssueDate(issueDate);
		purchase.setSupplier(supplier);
		return purchase;
	}

	
	
}
