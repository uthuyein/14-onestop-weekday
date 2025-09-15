package com.jdc.mkt.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Teacher;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaFactory {

	static EntityManagerFactory emf;
	
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("inheritance-mappings");
	}
	
	@AfterAll
	static void closeEmf() {
		if(null != emf && emf.isOpen()) {
			emf.close();
		}
	}
	
	@Test
	void test() {
		var em = emf.createEntityManager();
		var a1 = new Teacher();
		a1.setLoginId("andrew");
		a1.setName("Andrew");
		a1.setPassword("123");
		em.getTransaction().begin();
		em.persist(a1);
		em.getTransaction().commit();
		em.close();
	}
}
