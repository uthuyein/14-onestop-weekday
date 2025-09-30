package com.jdc.mkt.test.criteria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Category_;
import com.jdc.mkt.entity.Product;
import com.jdc.mkt.entity.Product_;
import com.jdc.mkt.test.util.JpaFactory;

public class CriteriaQueryTest extends JpaFactory {
	
	@ParameterizedTest
	@CsvSource("s,2")
	/*
	 * select c from Category c 
	 * join c.products p 
	 * where lower(p.name) like lower(:name)
	 */
	void selectCategoryByPNameLikeWith(String name,int res) {
		
		var cb = em.getCriteriaBuilder();
		var cq = cb.createQuery(Category.class);
		
		//select c from Category c
		var root = cq.from(Category.class);
		cq.select(root);
		
		//join c.products p
		var jProduct = root.join(Category_.products);
		
		cq.where(cb.like(
				cb.lower(jProduct.get(Product_.name)),
				name.toLowerCase().concat("%")));
		
		var query = em.createQuery(cq);
		var list = query.getResultList();
		assertEquals(res, list.size());
	}
	
	
	@Test
	@Disabled
	//select p from Product p where p.dtPrice between :from and :to
	void selectProductByDtPriceBetween() {
		var cb = em.getCriteriaBuilder();
		var cq = cb.createQuery(Product.class);
		var root = cq.from(Product.class);
		cq.select(root);
		
		//where p.dtPrice between :from and :to
		var predicate = cb.between(root.get(Product_.dtPrice), 10.00, 30.00);
		cq.where(predicate);
		
		var query = em.createQuery(cq);
		var list = query.getResultList();
		list.forEach(p -> System.out.println(p.getName()));
	}

	@Test
	@Disabled
	//select p from Product p where p.category.name = :name
	void selectProductByCategoryName() {
		var cb = em.getCriteriaBuilder();
		var cq = cb.createQuery(Product.class);
		
		//select p from Product p
		var root = cq.from(Product.class);
		cq.select(root);
		
		//Predicate
		//where p.category.name = :name
		cq.where(cb.equal(root.get(Product_.category).get(Category_.name), "Handset"));
		
		var query = em.createQuery(cq);
		var list = query.getResultList();
		list.forEach(p -> System.out.println(p.getName()));
	}

	@Test
	@Disabled
	@Order(1)
	// select c from Category c
	void selectAllCategory() {
		// Create CriteriaBuilder Object
		var cb = em.getCriteriaBuilder();

		// Create CriteriaQuery Object of What we need obj
		var cq = cb.createQuery(Category.class);

		var root = cq.from(Category.class);
		cq.select(root);

		var query = em.createQuery(cq);
		List<Category> list = query.getResultList();
		list.forEach(c -> System.out.println(c.getName()));
	}
}
