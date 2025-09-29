package com.jdc.mkt.test.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;
import com.jdc.mkt.test.util.JpaFactory;

/*
 * 1.single field object,join query does not need (use .dot)
 * 2.collection type need join from related entity
 */
public class D_Join_Query extends JpaFactory {
	
	
	@ParameterizedTest
	@CsvSource("Handset,2")
	void selectProductByCatNameWithSingleField(String name,int res) {
		var query = "select p from Product p where p.category.name = :name";
		//var query = "select p from Product p join p.category c where c.name = :name";
		var jpql = em.createQuery(query,Product.class);
		jpql.setParameter("name", name);
		var list = jpql.getResultList();
		assertEquals(res, list.size());
	}
	
	@ParameterizedTest
	@CsvSource("s,2")
	/*
	 * select * form category_tbl c
	 * join product_tbl p on p.category_id = c.id
	 * where lower(p.name) like lower(?)
	 */
	void selectCategoryByPNameLikeWithJpql(String name,int res) {
		var query = "select c from Category c join c.products p where lower(p.name) like lower(:name)";
		var jpql = em.createQuery(query,Category.class);
		jpql.setParameter("name", name.concat("%"));
		var list = jpql.getResultList();
		assertEquals(res, list.size());
	}
	
	
	
	
}
