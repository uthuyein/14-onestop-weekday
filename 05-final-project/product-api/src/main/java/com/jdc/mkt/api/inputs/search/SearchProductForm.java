package com.jdc.mkt.api.inputs.search;

import java.util.ArrayList;
import java.util.List;

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
		List<Predicate> params = new ArrayList<>();
		System.out.println("Before key :::::::::::: "+keywords);
		
		if (null != id) {
			params.add(cb.equal(root.get(Product_.id), id));
		}
//		if(!StringUtils.isBlank(keywords)) {
//			System.out.println("key :::::::::::: "+keywords);
//			params.add(cb.like(cb.lower(root.get(Product_.name)), keywords.toLowerCase().concat("%")));
//		}

		
		//	params.add(cb.equal(root.get(Product_.category).get(Category_.name), keywords));

		

		return params.toArray(s -> new Predicate[s]);
	}
}
