package com.jdc.mkt.test.jpql;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.test.util.JpaFactory;

public class C_NamedQuery extends JpaFactory{

	@Test
	void selectAllCategoryTest() {
		var query = em.createNamedQuery("selectAllCategory", Category.class);
		var stream = query.getResultStream();
		System.out.println(stream.count());
	}
}
