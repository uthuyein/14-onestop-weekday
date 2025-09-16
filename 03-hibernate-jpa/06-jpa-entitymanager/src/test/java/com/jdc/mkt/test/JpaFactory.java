package com.jdc.mkt.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@TestMethodOrder(OrderAnnotation.class)
public class JpaFactory {

	static EntityManagerFactory emf;
	
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("jpa-entitymanager");
	}
	
	@AfterAll
	static void closeEmf() {
		if(null != emf && emf.isOpen()) {
			emf.close();
		}
	}
	
	Product getProduct(String name,double dtPrice,Category cat) {
		var p = new Product();
		p.setName(name);
		p.setDtPrice(dtPrice);
		p.setWsPrice(dtPrice);
		p.setCategory(cat);
		return p;
	}
	
	Category getCategory(String name,int catId) {
		
		var c = new Category();	
		Category sub = null;
		
		if(catId > 0) {
			sub = new Category();
			sub.setId(catId);
		}		
		c.setName(name);
		c.setCategory(sub);
		
		return c;
	}
}
