package com.jdc.mkt.api.outputs;

import java.time.LocalDate;

import com.jdc.mkt.model.entities.Product;
import com.jdc.mkt.model.entities.ProductPrice;
import com.jdc.mkt.model.entities.ProductPrice_;
import com.jdc.mkt.model.entities.Product_;
import com.jdc.mkt.model.entities.Purchase;
import com.jdc.mkt.model.entities.PurchaseDetail;
import com.jdc.mkt.model.entities.PurchaseDetail_;
import com.jdc.mkt.model.entities.Purchase_;
import com.jdc.mkt.model.entities.Size;
import com.jdc.mkt.model.entities.Size_;
import com.jdc.mkt.model.entities.Supplier;
import com.jdc.mkt.model.entities.Supplier_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Root;

public record SelectPurchaseDetail(
		Purchase purchase,
		Supplier supplier,
		ProductPrice productPrice,
		Double price,
		Product product,
		Size size,
		Integer qty,
		LocalDate issueDate
		
		) {
	
	public static SelectPurchaseDetail from(PurchaseDetail p) {
		return new SelectPurchaseDetail(
				p.getPurchase(),
				p.getPurchase().getSupplier(),
				p.getProductPrice(),
				p.getProductPrice().getPrice(),
				p.getProductPrice().getProduct(),
				p.getProductPrice().getSize(),
				p.getQty(),
				p.getPurchase().getIssueDate());
	}

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
