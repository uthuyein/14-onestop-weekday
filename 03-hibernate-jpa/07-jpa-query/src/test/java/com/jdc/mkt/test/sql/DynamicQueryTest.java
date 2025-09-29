package com.jdc.mkt.test.sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Product;
import com.jdc.mkt.entity.dto.SelectCNamePNameWithCount;
import com.jdc.mkt.test.util.JpaFactory;

public class DynamicQueryTest extends JpaFactory{

	//@Test
	/*
	 * use createNativeQuery method with result class type
	 * 1.param index (?) ,(?1)
	 * 2.name param
	 */
	void selectProductByNameLike() {
		var query = "select * from product_tbl p where lower(p.name) like lower(:name)";
		var sql = em.createNativeQuery(query,Product.class);
		sql.setParameter("name", "t".concat("%"));
		var product = (Product) sql.getSingleResult();
		assertEquals("Tennis Racket", product.getName());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	void selectProductNameAndCountByCategory() {
		var query = """
					select c.name category,p.name product,count(*) qty from product_tbl p
					join category_tbl c on p.category_id = c.id
					group by c.name,p.name
					""";
		var sql = em.createNativeQuery(query,"selectCNamePNameWithCount");
		List<SelectCNamePNameWithCount> list = sql.getResultList();
		list.forEach(s -> System.out.println(s.category()+"\t"+s.product()+"\t"+s.count()));
	}
}









