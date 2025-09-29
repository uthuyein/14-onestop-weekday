package com.jdc.mkt.test.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.dto.SelectCNameWithCount;
import com.jdc.mkt.test.util.JpaFactory;

public class E_Projection_Query extends JpaFactory{

	/*
	 * select c.name,count(*) from product_tbl p
	 * join category_tbl c on p.category_id = c.id
	 * where c.name = 'Handset' group by c.name
	 * =====
	 * Handset 2
	 */
	@Test
	void countByCategory() {
		var query = """
				select new com.jdc.mkt.entity.dto.SelectCNameWithCount(
				c.name,count(p)) from Product p 
				join p.category c where c.name = :name
				group by c.name
				""";
		var jpql = em.createQuery(query,SelectCNameWithCount.class);
		jpql.setParameter("name", "Handset");
		var selector = jpql.getSingleResult();
		System.out.println(selector.category()+"\t"+selector.count());
		assertEquals(2,selector.count());
	}
}
