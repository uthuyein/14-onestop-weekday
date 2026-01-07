package com.jdc.mkt.api.inputs;

import com.jdc.mkt.model.entities.Address;
import com.jdc.mkt.model.entities.Contact;
import com.jdc.mkt.model.entities.Customer;

public record CustomerForm(
		String name,
		String state,
		String township,
		String street,
		String email,
		String primary,
		String secondary
		) {

	public Customer entity(Integer id) {
		var cu = new Customer();
		cu.setId(id);
		cu.setName(name);
		
		var ad = new Address();
		ad.setState(state);
		ad.setTownship(township);
		ad.setStreet(street);
		
		var con = new Contact();
		con.setEmail(email);
		con.setPrimaryPhone(primary);
		con.setSecondaryPhone(secondary);
		
		cu.setAddress(ad);
		cu.setContact(con);
		
		return cu;
	}

}
