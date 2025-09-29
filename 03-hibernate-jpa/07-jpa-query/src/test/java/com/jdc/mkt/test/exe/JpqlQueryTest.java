package com.jdc.mkt.test.exe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.entity.Product;
import com.jdc.mkt.test.util.JpaFactory;

public class JpqlQueryTest extends JpaFactory{

	@ParameterizedTest
	@CsvSource("Handset,2")
	/*
	 * select * from product_tbl p join 
	 * category_tbl on p.category_id = c.id
	 * where c.name = ?
	 */
	void selectProductByCatNameWithJpql(String name,int res) {
		var query = em.createQuery("select p from Product p where p.category.name = :name",Product.class);
		query.setParameter("name", name);
		var list = query.getResultList();
		assertEquals(res, list.size());
	}
}





