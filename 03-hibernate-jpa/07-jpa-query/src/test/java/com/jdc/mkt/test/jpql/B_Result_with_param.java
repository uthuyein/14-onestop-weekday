package com.jdc.mkt.test.jpql;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.entity.Product;
import com.jdc.mkt.test.util.JpaFactory;

public class B_Result_with_param extends JpaFactory {

	@Test
	@Order(1)
	void getResultListTest() {
		var query = em.createQuery("select p from Product p where p.dtPrice = p.wsPrice", Product.class);
		var list = query.getResultList();
		System.out.println("List Size : " + list.size());
	}
	
	
	@Order(2)
	@ParameterizedTest
	@CsvSource({
		"10,30"
	})
	void getResultStreamWithIndexParamTest(double from ,double to) {
		var query = em.createQuery("select p from Product p where p.dtPrice between ?2 and ?1 ", Product.class);
		query.setParameter(2, from);
		query.setParameter(1, to);
		
		var stream = query.getResultStream();
		System.out.println("Stream Count : " + stream.count());
	}
	
	@Order(3)
	@ParameterizedTest
	@CsvSource({
		"10,30"
	})
	void getSingleResultWithNameParamTest(double from ,double to) {
		var query = em.createQuery("select count(p) from Product p where p.dtPrice between :pFrom and :pTo ");
		query.setParameter("pFrom", from);
		query.setParameter("pTo", to);
				
		var obj = query.getSingleResult();
		System.out.println("Object : " + obj);
	}
	
	@Test
	@Order(4)
	void executeUpdateTest() {
		em.getTransaction().begin();
		var query = em.createQuery("delete from VoucherDetail v where v.id.id = 1");
		var row = query.executeUpdate();
		System.out.println("Row count : " + row);
		em.getTransaction().commit();
	}
}
