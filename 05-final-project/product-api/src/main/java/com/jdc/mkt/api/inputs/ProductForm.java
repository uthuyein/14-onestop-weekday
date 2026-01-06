package com.jdc.mkt.api.inputs;


import java.util.ArrayList;
import java.util.List;

import com.jdc.mkt.api.outputs.SelectProduct;
import com.jdc.mkt.model.entities.Category;
import com.jdc.mkt.model.entities.Category_;
import com.jdc.mkt.model.entities.Product;
import com.jdc.mkt.model.entities.Product_;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductForm(
		Integer id,
		@NotBlank(message = "Please type product name !")
		String name,
		@NotNull(message = "Please select category !")
		Integer catId,
		String catName,
		Boolean isActive
		) {

	public Product entity(Integer id) {
		var p = new Product();
		p.setId(id);
		p.setName(name);
		
		var c = new Category();
		c.setId(catId);
		c.setName(catName);
		p.setCategory(c);
		
		return p;
	}

	public Predicate[] where(CriteriaBuilder cb,CriteriaQuery<SelectProduct> cq, Root<Product> root) {
		List<Predicate> params = new ArrayList<>();
		
		if(null != id) {
			params.add(cb.equal(root.get(Product_.id), id));
		}
		
		if(null != catId) {
			params.add(cb.equal(root.get(Product_.category).get(Category_.id), id));
		}
		
		if(!StringUtils.isBlank(catName)) {
			params.add(cb.equal(root.get(Product_.category).get(Category_.name), catName));
		}
		
		if(!StringUtils.isBlank(name)) {
			params.add(cb.like(cb.lower(root.get(Product_.name)), name.toLowerCase().concat("%")));
		}	
		
		return params.toArray(s -> new Predicate[s]);
	}

}
