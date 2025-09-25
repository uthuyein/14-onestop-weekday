package com.jdc.mkt.test;

import org.junit.jupiter.api.Test;

public class E_CallBack_And_Listerner extends JpaFactory{

	
	@Test
	void categoryTest() {
		var c = getCategory("Test", 0);
		var p = getProduct("test Product", 33330, c);
		var em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(p);
		//c.setName("Change test");
		em.getTransaction().commit();
	}
	
}
