package com.jdc.mkt.api.inputs.search;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.mkt.model.entities.Address;
import com.jdc.mkt.model.entities.Address_;
import com.jdc.mkt.model.entities.Contact;
import com.jdc.mkt.model.entities.Contact_;
import com.jdc.mkt.model.entities.Customer;
import com.jdc.mkt.model.entities.Customer.MemberType;
import com.jdc.mkt.model.entities.Customer_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCustomerForm {

	private String name;
	private MemberType memberType;
	private String address;
	private String email;
	private String phone;
	
	public Predicate[] where(
			CriteriaBuilder cb, 
			Root<Customer> root,
			Join<Customer, Contact> joinCont,
			Join<Customer, Address> joinAdd) {
		var params = new ArrayList<Predicate>();
	
		if(StringUtils.hasLength(name)) {
			params.add(cb.like(
					cb.lower(root.get(Customer_.name)), 
					name.toLowerCase().concat("%")));
		}
		
		if(null != memberType) {
			params.add(cb.equal(root.get(Customer_.memberType), memberType));
		}
		
		if(StringUtils.hasLength(address)) {
			params.add(cb.or(
					cb.equal(joinAdd.get(Address_.state), address) ,
					cb.equal(joinAdd.get(Address_.township), address),
					cb.like(cb.lower(joinAdd.get(Address_.street)), address.toLowerCase().concat("%"))));
		}
		
		if(StringUtils.hasLength(email)) {
			params.add(cb.equal(joinCont.get(Contact_.email), email));
		}
		
		if(StringUtils.hasLength(phone)) {
			params.add(cb.or(cb.equal(joinCont.get(Contact_.primaryPhone), phone),
					cb.equal(joinCont.get(Contact_.secondaryPhone), phone)));
		}
		
		return params.toArray(q -> new Predicate[q]);
	}
}
