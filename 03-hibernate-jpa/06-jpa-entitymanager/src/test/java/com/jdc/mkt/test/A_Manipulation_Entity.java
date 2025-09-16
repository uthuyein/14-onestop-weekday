package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Category;

public class A_Manipulation_Entity extends JpaFactory{

	@Test
	@Order(1)
	void persistTest() {
		var em = emf.createEntityManager();
		
		//To Be Transient State
		var category = getCategory("Electronics", 0);
		var product = getProduct("EmergicyLight", 15000.00, category);
				
		em.getTransaction().begin();
			
		//To Be Managed State
		em.persist(category);
		em.persist(product);	
		//assertTrue(em.contains(category));
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	@Test
	@Order(2)
	void findTest() {
		var em = emf.createEntityManager();
		
		//To Be Managed State
		var c = em.find(Category.class, 1);
		assertNotNull(c);
		assertTrue(em.contains(c));
		c.setName("Electronic");
		
		assertEquals("Electronics", c.getName());
		em.close();
	}
	
	@Test
	@Disabled
	@Order(3)
	void mergeTest() {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		
		//To Be Managed State
		var c = em.find(Category.class, 1);
		assertNotNull(c);
		assertTrue(em.contains(c));
		
		//To Be Detached State
		em.detach(c);
		assertFalse(em.contains(c));
		
		//To Be Managed State
		var c1 = em.merge(c);
		assertFalse(em.contains(c));
		assertTrue(em.contains(c1));
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	@Disabled
	@Order(4)
	void removeTest() {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		
		//To Be Managed State
		var c = em.find(Category.class, 1);		
		assertNotNull(c);
		
		//To Be Removed State
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}
}
