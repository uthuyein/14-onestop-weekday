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
		String secondary) {

	public Customer entity(Customer cu) {
		cu.setName(name);

		var ad = new Address();
		ad.setState(state);
		ad.setTownship(township);
		ad.setStreet(street);

		var con = new Contact();
		con.setEmail(email);
		con.setPrimaryPhone(primary);
		con.setSecondaryPhone(secondary);

		if (null != cu.getId()) {
			ad.setId(cu.getAddress().getId());
			con.setId(cu.getContact().getId());
		}

		cu.setAddress(ad);
		cu.setContact(con);
		
		return cu;
	}

}
