package com.jdc.mkt.test.exe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.entity.Product;
import com.jdc.mkt.test.util.JpaFactory;

public class JpqlQueryTest extends JpaFactory{

	//@ParameterizedTest
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
	
	@Test
	void selectProductByNameLike() {
		var query = "select p from Product p where lower(p.name) like lower(:name)";
		var sql = em.createQuery(query,Product.class);
		sql.setParameter("name", "t".concat("%"));
		var product = (Product) sql.getSingleResult();
		assertEquals("Tennis Racket", product.getName());
		
	}
}





