package com.jdc.mkt.test.criteria;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Category_;
import com.jdc.mkt.entity.Product;
import com.jdc.mkt.entity.Product_;
import com.jdc.mkt.test.util.JpaFactory;

public class CriteriaQueryTest extends JpaFactory {
	
	@Test
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
