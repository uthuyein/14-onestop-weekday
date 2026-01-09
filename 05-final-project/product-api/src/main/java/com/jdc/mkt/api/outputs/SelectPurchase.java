package com.jdc.mkt.api.outputs;

import java.time.LocalDate;
import java.util.List;

import com.jdc.mkt.model.entities.Purchase;
import com.jdc.mkt.model.entities.PurchaseDetail;
import com.jdc.mkt.model.entities.Supplier;

public record SelectPurchase(
		Integer supplierId,
		LocalDate issueDate,
		Supplier supplier,
		List<PurchaseDetail> purchaseDetails
		) {

	public static SelectPurchase from(Purchase purchase) {
		return new SelectPurchase(purchase.getId(),purchase.getIssueDate(),purchase.getSupplier(),null);
	}
	
	public static SelectPurchase from(Purchase purchase,List<PurchaseDetail> purchaseDetails) {
		return new SelectPurchase(purchase.getId(),purchase.getIssueDate(),purchase.getSupplier(),purchaseDetails);
	}

}
