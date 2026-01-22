package com.jdc.mkt.api.outputs;

import java.time.LocalDate;

import com.jdc.mkt.model.entities.Category;
import com.jdc.mkt.model.entities.Product;
import com.jdc.mkt.model.entities.ProductPrice;
import com.jdc.mkt.model.entities.ProductPrice.PriceType;
import com.jdc.mkt.model.entities.ProductPrice_;
import com.jdc.mkt.model.entities.Product_;
import com.jdc.mkt.model.entities.Size;
import com.jdc.mkt.model.entities.Size_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record SelectProductPrice(
		Integer id,
		Product product,
		Category category,
		Size size,
		PriceType priceType,
		Double price,
		LocalDate createAt,
		LocalDate updateAt
		) {

	public static SelectProductPrice from(ProductPrice p) {
		return new SelectProductPrice(p.getId(),
				p.getProduct(),
				p.getProduct().getCategory(),
				p.getSize(),
				p.getPriceType(),
				p.getPrice(), 
				p.getCreateAt(), 
				p.getUpdateAt());
	}

	public static void select(CriteriaBuilder cb, CriteriaQuery<SelectProductPrice> cq, Root<ProductPrice> root) {
		cq.select(
				cb.construct(
						SelectProductPrice.class,
						root.get(ProductPrice_.id),
						root.get(ProductPrice_.product),
						root.get(ProductPrice_.product).get(Product_.category),
						root.get(ProductPrice_.size),
						root.get(ProductPrice_.priceType),
						root.get(ProductPrice_.price),
						root.get(ProductPrice_.createAt),
						root.get(ProductPrice_.updateAt))
				);
	}

}
