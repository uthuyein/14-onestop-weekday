package com.jdc.mkt.model.services;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.mkt.api.inputs.search.SearchCustomerForm;
import com.jdc.mkt.api.outputs.SelectCustomer;
import com.jdc.mkt.model.entities.Customer;
import com.jdc.mkt.model.repositories.CustomerRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo repo;
	
	public List<SelectCustomer> findBy(SearchCustomerForm form) {
		return repo.findBy(SearchFunction(form));
	}

	private Function<CriteriaBuilder,CriteriaQuery<SelectCustomer>> SearchFunction(SearchCustomerForm form) {
		return cb -> {
			var cq = cb.createQuery(SelectCustomer.class);
			var root = cq.from(Customer.class);
			SelectCustomer.select(cb,cq,root);
			cq.where(form.where(cb,root));
			return cq;
		};
	}

}
