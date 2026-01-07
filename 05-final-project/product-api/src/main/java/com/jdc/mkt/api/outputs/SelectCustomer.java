package com.jdc.mkt.api.outputs;

import com.jdc.mkt.model.entities.Address_;
import com.jdc.mkt.model.entities.Contact_;
import com.jdc.mkt.model.entities.Customer;
import com.jdc.mkt.model.entities.Customer_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record SelectCustomer(
		int id,
		String name,
		String email,
		String primaryPh,
		String secondaryPh,
		String state,
		String township,
		String street
		
		) {

	public static void select(CriteriaBuilder cb,CriteriaQuery<SelectCustomer> cq, Root<Customer> root) {
		var contact = root.join(Customer_.contact);
		var address = root.join(Customer_.address);
		
		cq.select(
				cb.construct(SelectCustomer.class,
						root.get(Customer_.id),
						root.get(Customer_.name),
						contact.get(Contact_.email),
						contact.get(Contact_.primaryPhone),
						contact.get(Contact_.secondaryPhone),
						address.get(Address_.state),
						address.get(Address_.township),
						address.get(Address_.street))
						
				);
	}

}
