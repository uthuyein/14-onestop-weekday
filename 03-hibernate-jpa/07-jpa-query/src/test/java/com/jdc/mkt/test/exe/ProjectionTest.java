package com.jdc.mkt.test.exe;

import com.jdc.mkt.test.util.JpaFactory;

/*
 * 1.select c.name,p.name,max(p.dt_price) from product_tbl p
 * join category_tbl c on p.category_id = c.id
 * group by c.name,p.name
 */
public class ProjectionTest extends JpaFactory{

	void selectCNamePNameAndMaxDtPriceTest() {
		
	}
}
