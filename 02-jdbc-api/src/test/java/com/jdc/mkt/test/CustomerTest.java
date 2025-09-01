package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.mkt.services.CustomerServiceWithPreparedStatement;
import com.jdc.mkt.services.CustomerServiceWithStatement;
import com.jdc.mkt.services.ServiceInt;

@TestMethodOrder(OrderAnnotation.class)
public class CustomerTest {
	
	static ServiceInt service;
	
	@BeforeAll
	static void init() {
		//service = new CustomerServiceWithStatement();
		service = new CustomerServiceWithPreparedStatement();
		service.resetCustomerTable();
	}
	
	

	@Order(1)
	@ParameterizedTest	
	@ValueSource(strings = {"Andrew", "William", "John", "Charles", "George","Arnel"})
	void testInsert(String name) {
		var row = service.save(name);
		assertEquals(1, row);
	}
	
	@Order(2)
	//@ParameterizedTest
	@CsvSource({
		" Andrew ss,,,1",
		",Gold,,2",
		"John Smith,Diamond,false,3"
		 })
	void testUpdate(String name,String memberType,Boolean active ,int id) {
		var row = service.update(name, memberType, active, id);
		assertEquals(1, row);
	}
	
	@Order(3)
	@ParameterizedTest
	@CsvSource({
		"a,,,,2",
		",,,,6"
		 })
	void testFind(String name,String memberType,Boolean active ,Integer id,int size) {
		var list = service.find(name, memberType, active, id);
		assertEquals(size, list.size());
	}
	
//	@AfterAll
//	static void testend() {
//		System.out.println("Test End");
//	}
//	@BeforeEach
//	void testeach() {
//		System.out.println("Before Each Test");
//	}
//	@AfterEach
//	void afterEach() {
//		System.out.println("After Each Test");
//	}
}
