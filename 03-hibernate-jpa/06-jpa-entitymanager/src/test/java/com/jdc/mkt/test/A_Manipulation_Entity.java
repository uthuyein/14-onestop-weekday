package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Category;

public class A_Manipulation_Entity extends JpaFactory{

	@Test
	@Order(1)
	void persistTest() {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		var electronic = getCategory("Electronics", 0);
		
		em.persist(electronic);
		assertTrue(em.contains(electronic));
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	@Order(2)
	void findTest() {
		var em = emf.createEntityManager();
		var c = em.find(Category.class, 1);
		assertNotNull(c);
		assertEquals("Electronics", c.getName());
		em.close();
	}
	
	@Test
	@Order(3)
	void mergeTest() {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		var c = em.find(Category.class, 1);
		assertNotNull(c);
		c.setName("Electronic");
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	@Order(4)
	void removeTest() {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		var c = em.find(Category.class, 1);
		assertNotNull(c);
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}
}
