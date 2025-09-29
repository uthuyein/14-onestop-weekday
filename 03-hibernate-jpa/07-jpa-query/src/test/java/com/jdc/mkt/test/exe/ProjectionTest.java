package com.jdc.mkt.test.exe;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.dto.SelectCNamePNameWithCount;
import com.jdc.mkt.entity.dto.SelectCNamePNameWithMax;
import com.jdc.mkt.test.util.JpaFactory;

/*
 * 1.select c.name,p.name,max(p.dt_price) from product_tbl p
 * join category_tbl c on p.category_id = c.id
 * group by c.name,p.name
 */
public class ProjectionTest extends JpaFactory{

	@Order(1)
	@Test
	void selectCNamePNameAndMaxDtPriceWithJpqlTest() {
		var query = """
					select c.name category,p.name product,max(p.dtPrice) price from Product p
				  	join p.category c group by c.name,p.name
					""";
		var jpql = em.createQuery(query,SelectCNamePNameWithMax.class);
		var dto = jpql.getResultList();
		System.out.println(dto);
	}
	
	@Order(2)
	void selectCNamePNameAndMaxDtPriceWithSqlTest() {
		var query = """
					select c.name,p.name,max(p.dt_price) from product_tbl p
				  	join category_tbl c on p.category_id = c.id
				  	group by c.name,p.name
					""";
		var jpql = em.createNativeQuery(query,"");
		var dto = jpql.getSingleResult();
		System.out.println(dto);
	}
	
	//@Test
	@Order(3)
	void selectProductNameAndCountByCategory() {
		var query = """
					select c.name category,p.name product,count(*) qty from Product p
					join p.category c group by c.name,p.name
					""";
		var sql = em.createQuery(query,SelectCNamePNameWithCount.class);
		List<SelectCNamePNameWithCount> list = sql.getResultList();
		list.forEach(s -> System.out.println(s.category()+"\t"+s.product()+"\t"+s.count()));
	}
}
