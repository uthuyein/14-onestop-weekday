package com.jdc.mkt.test.criteria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Category_;
import com.jdc.mkt.entity.Product;
import com.jdc.mkt.entity.Product_;
import com.jdc.mkt.entity.VoucherDetail;
import com.jdc.mkt.entity.VoucherDetail_;
import com.jdc.mkt.entity.Voucher_;
import com.jdc.mkt.entity.dto.SelectCNameWithCount;
import com.jdc.mkt.test.util.JpaFactory;

public class ProjectionQueryTest extends JpaFactory{
	
	/*
	 * select new com.jdc.mkt.entity.dto.SelectCNameWithCount(
	   c.name,count(p)) from Product p 
	   join p.category c group by c.name
	 * =====
	 * Handset 2
	 */
	@Test
	void countByCategory() {
		
		var cb = em.getCriteriaBuilder();
		var cq = cb.createQuery(SelectCNameWithCount.class);
		
		var root = cq.from(Product.class);
		var path = root.get(Product_.category).get(Category_.name);
		cq.select(
				cb.construct(
						SelectCNameWithCount.class,
						path,
						cb.count(root)));
		
		cq.groupBy(path);
		var jpql = em.createQuery(cq);
		var list = jpql.getResultList();
		list.forEach(s -> System.out.println(s.category()+"\t"+s.count()));
	}
	
	@Test
	//select sum(vd.subTotal) from VoucherDetail vd where vd.voucher.id = :id
	void selectTotalSumByVoucherId() {
		var cb = em.getCriteriaBuilder();
		var cq = cb.createQuery(Double.class);
		
		var root = cq.from(VoucherDetail.class);		
		cq.multiselect(cb.sum(root.get(VoucherDetail_.subTotal)));
		
		cq.where(cb.equal(root.get(VoucherDetail_.voucher).get(Voucher_.id), 1));
		
		var query = em.createQuery(cq);
		var res = query.getSingleResult();
		System.out.println(res);
		
	}

	@Test
	//select count(p) from Product p where p.category.name = :name
	void selectProductCountByCatName() {
		var cb = em.getCriteriaBuilder();
		var cq = cb.createQuery(Long.class);
		var root = cq.from(Product.class);
		cq.select(cb.count(root));
		cq.where(
				cb.equal(
						root.get(Product_.category)
						.get(Category_.name), "Handset"));
		
		var query = em.createQuery(cq);
		var res = query.getSingleResult();
		System.out.println(res);
	}
}
