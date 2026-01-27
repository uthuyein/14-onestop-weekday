package com.jdc.mkt.api.inputs.search;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.mkt.model.entities.Category_;
import com.jdc.mkt.model.entities.ProductPrice;
import com.jdc.mkt.model.entities.ProductPrice_;
import com.jdc.mkt.model.entities.Product_;
import com.jdc.mkt.model.entities.Size_;
import com.jdc.mkt.model.entities.ProductPrice.PriceType;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchProductPriceForm {

	private String category;
	private String product;
	private String size;
	private PriceType priceType;
	private LocalDate dateFrom;
	private LocalDate dateTo;

	public Predicate[] where(CriteriaBuilder cb, Root<ProductPrice> root) {
		var params = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(category)) {
			params.add(cb.equal(root.get(ProductPrice_.product).get(Product_.category).get(Category_.name), category));
		}
		
		if(StringUtils.hasLength(product)) {
			params.add(cb.like(cb.lower(root.get(ProductPrice_.product).get(Product_.name)), product.concat("%")));
		}
		
		if(StringUtils.hasLength(size)) {
			params.add(cb.equal(root.get(ProductPrice_.size).get(Size_.name), size));
		}
		
		if(null != priceType) {
			params.add(cb.equal(root.get(ProductPrice_.priceType), priceType));
		}
				
		if(null != dateFrom) {
			params.add(
					cb.or(
					cb.greaterThanOrEqualTo(root.get(ProductPrice_.createAt), dateFrom),
					cb.greaterThanOrEqualTo(root.get(ProductPrice_.updateAt), dateFrom))
					);
			}
		
		if(null != dateTo) {
			params.add(
					cb.or(
					cb.lessThanOrEqualTo(root.get(ProductPrice_.createAt), dateTo),
					cb.lessThanOrEqualTo(root.get(ProductPrice_.updateAt), dateTo))
					);
		   }
		params.add(cb.equal(root.get(ProductPrice_.isActive), true));
			
		return params.toArray(Predicate[]:: new);
	}
}
