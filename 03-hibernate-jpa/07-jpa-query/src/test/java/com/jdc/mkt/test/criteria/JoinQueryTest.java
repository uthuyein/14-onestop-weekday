package com.jdc.mkt.test.criteria;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Contact_;
import com.jdc.mkt.entity.Customer;
import com.jdc.mkt.entity.Customer_;
import com.jdc.mkt.entity.dto.SelectCuNameCuTypeAndEmail;
import com.jdc.mkt.test.util.JpaFactory;

public class JoinQueryTest extends JpaFactory{

	/*
	 * select new com.jdc.mkt.entity.dto.SelectCuNameCuTypeAndEmail(
	 * cu.name,cu.memberType,c.email) from Customer cu
	 * join cu.contact c where c.email = :email
	 */
	@Test
	void selectCustomerByEmail() {
		var cb = em.getCriteriaBuilder();
		var cq = cb.createQuery(SelectCuNameCuTypeAndEmail.class);	
		var root = cq.from(Customer.class);
		
		//With Use join
		var contact = root.join(Customer_.contact);
		var path = contact.get(Contact_.email);
		
		//Without use join
		//var path = root.get(Customer_.contact).get(Contact_.email);
		
		cq.multiselect(List.of(
				root.get(Customer_.name),
				root.get(Customer_.memberType),
				path));
		cq.where(cb.and(
				cb.equal(path, "bob.smith@example.com"),
				cb.equal(contact.get(Contact_.primaryPhone), "0933333333")));
		
		var query = em.createQuery(cq);
		var res = query.getSingleResult();
		System.out.println(res.customer()+"\t"+res.memberType()+"\t"+res.email());
	}
}
