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
	
	private Integer id;
	private Boolean isActive;
	private String keywords;
	
	

	public  Predicate[] where(CriteriaBuilder cb, Root<Product> root) {
		var params = new ArrayList<Predicate>();
		
		if (null != id) {
			params.add(cb.equal(root.get(Product_.id), id));
		}
		if(StringUtils.hasLength(keywords)) {
			params.add(
					cb.or(
					cb.like(cb.lower(root.get(Product_.name)), keywords.toLowerCase().concat("%")),
					cb.equal(root.get(Product_.category).get(Category_.name), keywords)));
		}		

		return params.toArray( s -> new Predicate[s]);
	}
}
