package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Product;

import jakarta.persistence.EntityNotFoundException;

public class B_find_vs_getReference extends JpaFactory {

	@Test
	@Disabled
	@Order(1)
	void findTest() {
		var em = emf.createEntityManager();

		// If found,Entity will return
		var p1 = em.find(Product.class, 2);
		assertNotNull(p1);
		em.clear();
		assertEquals("Python", p1.getCategory().getName());
		
		
		// If not found,Null will return
		var p2 = em.find(Product.class, 8);
		assertNull(p2);

		em.close();
	}

	@Test
	@Order(2)
	void getReference() {

		var em = emf.createEntityManager();

		// If found,Proxy will return before use getter.
		var p1 = em.getReference(Product.class, 2);
		assertNotNull(p1);
		em.clear();
		
		// If not found,Proxy  will return
		var p2 = em.getReference(Product.class, 8);
		assertNotNull(p2);
		assertThrows(EntityNotFoundException.class,() -> p2.getName());

		em.close();
	}
	
	
}
