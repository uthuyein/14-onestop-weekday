package com.jdc.mkt.test.jpql;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.test.util.JpaFactory;

public class A_Query_vs_TypeQuery extends JpaFactory {

	@Test
	@Order(1)
	void queryTest() {
		var query = em.createQuery("select c from Category c");
		var list = query.getResultList();
		System.out.println("Category List Size :"+list.size());
	}
	@Test
	@Order(2)
	void updateWithTypeQueryTest() {
		em.getTransaction().begin();
		var query = em.createQuery("update Category c set c.active = false  where c.active = true");
		var row = query.executeUpdate();
		System.out.println("Row Count :"+ row);
		em.getTransaction().commit();
	}

	@Test
	@Order(3)
	void typeQueryTest() {
		var query = em.createQuery("select c from Category c", Category.class);
		var list = query.getResultList();
		System.out.println("Category List Size :"+list.size());
	}

	
}
