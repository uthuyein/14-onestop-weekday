package com.jdc.mkt.api.inputs;

import com.jdc.mkt.model.entities.Address;
import com.jdc.mkt.model.entities.Contact;
import com.jdc.mkt.model.entities.Customer;
import com.jdc.mkt.model.entities.Customer.MemberType;

public record CustomerForm(
		Integer id,
		String name,
		MemberType memberType,
		String email, 
		String primary,
		String secondary,
		String state,
		String township,
		String street, 
		boolean isActive) {

	public Customer entity(Customer cu) {
		cu.setId(id);
		cu.setName(name);
		cu.setMemberType(memberType);
		cu.setActive(isActive);
		
		
		var ad = cu.getId() == null ? new Address():cu.getAddress();
		ad.setState(state);
		ad.setTownship(township);
		ad.setStreet(street);

		var con =  cu.getId() == null ?new Contact():cu.getContact();
		con.setEmail(email);
		con.setPrimaryPhone(primary);
		con.setSecondaryPhone(secondary);

		

		cu.setAddress(ad);
		cu.setContact(con);
		
		return cu;
	}

}
