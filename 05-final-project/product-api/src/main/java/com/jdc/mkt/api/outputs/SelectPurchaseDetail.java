package com.jdc.mkt.api.outputs;

import java.time.LocalDate;

import com.jdc.mkt.model.entities.ProductPrice;
import com.jdc.mkt.model.entities.ProductPrice_;
import com.jdc.mkt.model.entities.Product_;
import com.jdc.mkt.model.entities.Purchase;
import com.jdc.mkt.model.entities.PurchaseDetail;
import com.jdc.mkt.model.entities.PurchaseDetail_;
import com.jdc.mkt.model.entities.Purchase_;
import com.jdc.mkt.model.entities.Size_;
import com.jdc.mkt.model.entities.Supplier_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Root;

public record SelectPurchaseDetail(
		Integer purchaseId,
		Integer supplierId,
		String supplier,
		Integer productPriceId,
		Double price,
		String product,
		String size,
		Integer qty,
		LocalDate issueDate
		
		) {

	public static void select(CriteriaBuilder cb, CriteriaQuery<SelectPurchaseDetail> cq, Root<Purchase> root,
			ListJoin<Purchase, PurchaseDetail> details,Join<PurchaseDetail, ProductPrice> price) {
		
		 cq.select(
				cb.construct(SelectPurchaseDetail.class,
						root.get(Purchase_.id),
						root.get(Purchase_.supplier).get(Supplier_.id),
						root.get(Purchase_.supplier).get(Supplier_.name),
						price.get(ProductPrice_.id),
						price.get(ProductPrice_.price),
						price.get(ProductPrice_.product).get(Product_.name),
						price.get(ProductPrice_.size).get(Size_.name),
						details.get(PurchaseDetail_.qty),
						root.get(Purchase_.issueDate)
						)
				);
	}

}
