package com.jdc.mkt.api.outputs;

import com.jdc.mkt.model.entities.Address;
import com.jdc.mkt.model.entities.Contact;
import com.jdc.mkt.model.entities.Customer;
import com.jdc.mkt.model.entities.Customer.MemberType;
import com.jdc.mkt.model.entities.Customer_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

public record SelectCustomer(
		Integer id,
		String name,
		MemberType memberType,
		Contact contact,
		Address address
				
		) {

	public static void select(
			CriteriaBuilder cb,
			CriteriaQuery<SelectCustomer> cq, 
			Root<Customer> root,
			Join<Customer, Contact> contact,
			Join<Customer, Address> address) {
		
		cq.select(
				cb.construct(SelectCustomer.class,
						root.get(Customer_.id),
						root.get(Customer_.name),
						root.get(Customer_.memberType),
						root.get(Customer_.contact),
						root.get(Customer_.address)));
	}

	public static SelectCustomer from(Customer cu) {
		return new SelectCustomer(
				cu.getId(),
				cu.getName(),
				cu.getMemberType(),
				cu.getContact(),
				cu.getAddress() 
				);
	}

}
