package com.jdc.mkt.api.inputs.search;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.mkt.model.entities.Category_;
import com.jdc.mkt.model.entities.ProductPrice;
import com.jdc.mkt.model.entities.ProductPrice_;
import com.jdc.mkt.model.entities.Product_;
import com.jdc.mkt.model.entities.Purchase;
import com.jdc.mkt.model.entities.PurchaseDetail;
import com.jdc.mkt.model.entities.Purchase_;
import com.jdc.mkt.model.entities.Size_;
import com.jdc.mkt.model.entities.Supplier_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class SearchPurchaseForm {

	private String supplier;
	private String product;
	private String category;
	private String size;

	private LocalDate fromDate;
	private LocalDate toDate;

	public Predicate[] where(CriteriaBuilder cb, Root<Purchase> root, 
			ListJoin<Purchase, PurchaseDetail> detail,
			Join<PurchaseDetail, ProductPrice> price) {

		var params = new ArrayList<Predicate>();

		if (StringUtils.hasLength(supplier)) {
			params.add(cb.equal(root
					.get(Purchase_.supplier)
					.get(Supplier_.name), supplier));
		}
		
		if (StringUtils.hasLength(product)) {
			params.add(cb.like(cb.lower(price
					.get(ProductPrice_.product)
					.get(Product_.name)), product));
		}
		
		if (StringUtils.hasLength(category)) {
			params.add(cb.equal(price
					.get(ProductPrice_.product)
					.get(Product_.category)
					.get(Category_.name), category));
		}
		
		if (StringUtils.hasLength(size)) {
			params.add(cb.equal(price
					.get(ProductPrice_.size)
					.get(Size_.name), size));
		}
		
		if(null != fromDate) {
			params.add(cb.greaterThanOrEqualTo(root.get(Purchase_.issueDate), fromDate));
		}
		
		if(null != toDate) {
			params.add(cb.lessThanOrEqualTo(root.get(Purchase_.issueDate), fromDate));
		}

		return params.toArray(Predicate[]::new);
	}

}
