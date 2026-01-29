package com.jdc.mkt.api.inputs.search;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.mkt.model.entities.Category_;
import com.jdc.mkt.model.entities.ProductPrice;
import com.jdc.mkt.model.entities.ProductPrice_;
import com.jdc.mkt.model.entities.Product_;
import com.jdc.mkt.model.entities.Size_;

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

	private String keyword;
	
	public Predicate[] where(CriteriaBuilder cb, Root<ProductPrice> root) {
		var params = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(keyword)) {
			params.add(cb.or(
					cb.like(cb.lower( root.get(ProductPrice_.product).get(Product_.category).get(Category_.name)), keyword.toLowerCase().concat("%")),
					cb.like(cb.lower(root.get(ProductPrice_.product).get(Product_.name)), keyword.toLowerCase().concat("%")),
					cb.like(cb.lower(root.get(ProductPrice_.size).get(Size_.name)), keyword.toLowerCase().concat("%")),
					cb.equal(root.get(ProductPrice_.priceType), keyword)
					));
			
		}
		
		
		params.add(cb.equal(root.get(ProductPrice_.isActive), true));
			
		return params.toArray(Predicate[]:: new);
	}
}
