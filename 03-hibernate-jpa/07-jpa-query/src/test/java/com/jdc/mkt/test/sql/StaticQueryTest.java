package com.jdc.mkt.test.sql;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.dto.SelectMemberAndCount;
import com.jdc.mkt.test.util.JpaFactory;

public class StaticQueryTest extends JpaFactory{

	@Test
	void selectAllCategory() {
		var sql = em.createNamedQuery("selectAllCategoryWithSql",Category.class);
		List<Category> list = sql.getResultList();
		System.out.println(list.size());
	}
	
	@Test
	void selectMemberAndCountTest() {
		var sql = em.createNamedQuery("selectMemberAndCount",SelectMemberAndCount.class);
		List<SelectMemberAndCount> list = sql.getResultList();
		list.forEach(s -> System.out.println(s.member()+"\t"+s.count()));
	}
}
