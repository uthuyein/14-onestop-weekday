package com.jdc.mkt.api.outputs;

import com.jdc.mkt.model.entities.Category;
import com.jdc.mkt.model.entities.Product;
import com.jdc.mkt.model.entities.Product_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record SelectProduct(
		Integer id,
		String name,
		Category category
		) {

	public static SelectProduct from(Product p) {
		return new SelectProduct(p.getId(), p.getName(), p.getCategory());
	}

	public static void select(CriteriaBuilder cb,CriteriaQuery<SelectProduct> cq, Root<Product> root) {
		cq.select(
				cb.construct(
						SelectProduct.class,
						root.get(Product_.id),
						root.get(Product_.name),
						root.get(Product_.category)
						)
				);
	}

}
