package com.jdc.mkt.api.inputs.search;

import java.util.ArrayList;

import com.jdc.mkt.model.entities.Customer;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCustomerForm {

	private String name;
	private String address;
	private String email;
	private String phone;
	
	public Predicate[] where(CriteriaBuilder cb, Root<Customer> root) {
		var params = new ArrayList<Predicate>();
		
		
		
		return params.toArray(q -> new Predicate[q]);
	}
}
