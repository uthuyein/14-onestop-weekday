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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCustomerForm {

	String type;
	String keyword;
	
	public Predicate[] where(
			CriteriaBuilder cb, 
			Root<Customer> root,
			Join<Customer, Contact> joinCont,
			Join<Customer, Address> joinAdd) {
		var params = new ArrayList<Predicate>();
		
		if(null != type) {
			params.add(cb.equal(root.get(Customer_.memberType),MemberType.valueOf(type)));
					
		}
		
		if(StringUtils.hasLength(keyword)) {
			params.add(cb.or(
					cb.like(cb.lower(root.get(Customer_.name)), keyword.toLowerCase().concat("%")),
					cb.equal(joinAdd.get(Address_.state), keyword) ,
					cb.equal(joinAdd.get(Address_.township), keyword),
					cb.like(cb.lower(joinAdd.get(Address_.street)), keyword.toLowerCase().concat("%")),
					cb.equal(joinCont.get(Contact_.email), keyword),
					cb.equal(joinCont.get(Contact_.primaryPhone), keyword),
					cb.equal(joinCont.get(Contact_.secondaryPhone), keyword)
					));
		}
		params.add(cb.equal(root.get(Customer_.isActive), true));
	
	
		return params.toArray(q -> new Predicate[q]);
	}
}
