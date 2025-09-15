package com.jdc.mkt.test;

import java.time.LocalDate;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Card;
import com.jdc.mkt.entity.Customer;

public class ConverterTest extends JpaFactory{

	
	@Test
	@Order(2)
	void findTest() {
		var em = emf.createEntityManager();
		var cu = em.find(Customer.class, 1);
		var card = cu.getCard();
		
		System.out.println(card.getNumber());
		em.close();
	}
	
	@Test
	@Order(1)
	void createTest() {
		var em = emf.createEntityManager();
		
		var card = new Card("AB-223422",
				LocalDate.of(2002, 03, 30),
				LocalDate.of(2028, 05, 22));
		
		var customer = new Customer();
		customer.setName("Andrew");
		customer.setCard(card);
		customer.setAge(30);
		
		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
		em.close();
	}
}
