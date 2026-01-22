package com.jdc.mkt.api.inputs.search;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.mkt.model.entities.Category_;
import com.jdc.mkt.model.entities.Product;
import com.jdc.mkt.model.entities.Product_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchProductForm {
	
	private Boolean isActive;
	private String keyword;
	
	

	public  Predicate[] where(CriteriaBuilder cb, Root<Product> root) {
		var params = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(keyword)) {
			params.add(
					cb.or(
					cb.like(cb.lower(root.get(Product_.name)), keyword.toLowerCase().concat("%")),
					cb.equal(root.get(Product_.category).get(Category_.name), keyword)));
		}		

		return params.toArray( s -> new Predicate[s]);
	}
}
