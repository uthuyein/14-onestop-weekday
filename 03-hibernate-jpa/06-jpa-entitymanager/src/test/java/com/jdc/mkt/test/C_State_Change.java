package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Product;

import jakarta.persistence.PersistenceException;

public class C_State_Change extends JpaFactory {

	@Test
	@Disabled
	@Order(3)
	void removeTest() {
		var em = emf.createEntityManager();
		em.getTransaction().begin();

		// To Be Managed
		var p1 = em.find(Product.class, 1);
		assertTrue(em.contains(p1));

		// To Be Removed
		em.remove(p1);
		assertFalse(em.contains(p1));
		
		// To Be Managed
		em.persist(p1);
		assertTrue(em.contains(p1));
		
		// To Be Detached
		em.detach(p1);
		assertFalse(em.contains(p1));

		assertThrows(IllegalArgumentException.class, () -> em.remove(p1));

		em.getTransaction().commit();
		em.close();
	}

	@Test
	@Disabled
	@Order(2)
	void mergeTest() {
		var em = emf.createEntityManager();
		em.getTransaction().begin();

		// To Be Transient
		var category = getCategory("Handset", 1);
		var product = getProduct("Samsung Glaxy zflip", 4400000.00, category);

		// To Be Managed
		var c1 = em.merge(category);
		product.setCategory(c1);

		var p1 = em.merge(product);
		assertTrue(em.contains(p1));

		// To Be Detached
		em.detach(p1);
		assertFalse(em.contains(p1));

		// To Be Managed
		var p2 = em.merge(p1);
		assertTrue(em.contains(p2));

		// To Be Removed
		em.remove(p2);
		assertFalse(em.contains(p2));

		assertThrows(IllegalArgumentException.class, () -> em.merge(p2));

		em.getTransaction().commit();
		em.close();
	}

	@Test
	//@Disabled
	@Order(1)
	void persistTest() {
		var em = emf.createEntityManager();
		em.getTransaction().begin();

		// To Be Transient
		var category = getCategory("Handset", 1);
		var product = getProduct("Samsung Glaxy zflip", 4400000.00, category);
		var product1 = getProduct("Samsung Glaxy note 11", 1000000.00, category);

		// To Be Managed
		//em.persist(category);
		em.persist(product);
		em.persist(product1);
		assertTrue(em.contains(product));

		// To Be Removed
		em.remove(product);
		assertFalse(em.contains(product));
//
//		// To Be Managed
//		em.persist(product);
//		assertTrue(em.contains(product));
//
//		// To Be Detached
//		em.detach(product);
//		assertFalse(em.contains(product));
//
//		assertThrows(PersistenceException.class, () -> em.persist(product));

		em.getTransaction().commit();
		em.close();
	}
}
