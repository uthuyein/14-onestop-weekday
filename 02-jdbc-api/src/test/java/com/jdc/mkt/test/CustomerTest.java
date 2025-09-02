package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.mkt.PrintLogger;
import com.jdc.mkt.dto.Customer;
import com.jdc.mkt.services.CustomerServiceWithPreparedStatement;
import com.jdc.mkt.services.ServiceInt;
import com.jdc.mkt.utils.anno.Connector;

@TestMethodOrder(OrderAnnotation.class)
@Connector
public class CustomerTest {
	
	static ServiceInt service;
	static PrintLogger logger;
	
	@BeforeAll
	static void init() {
		logger = PrintLogger.getInstance(CustomerTest.class);
		
		//service = new CustomerServiceWithStatement();
		service = new CustomerServiceWithPreparedStatement();
		
		service.resetCustomerTable();
	}
	
	

	@Order(1)
	@ParameterizedTest	
	@ValueSource(strings = {"Andrew", "William", "John", "Charles", "George","Arnel"})
	void testInsert(String name) {
		var row = service.save(name);
		//logger.printTableByStringQuery("select * from customer_tbl");
		assertEquals(1, row);
	}
	
	@Order(2)
	@ParameterizedTest
	@CsvSource({
		" Andrew ss,,,1",
		",Gold,,2",
		" John Smith,Diamond,,4"
		 })
	void testUpdate(String name,String memberType,Boolean active ,int id) {
		var row = service.update(name, memberType, active, id);
		logger.printTableByStringQuery("select * from customer_tbl");
		assertEquals(1, row);
	}
	
	@Order(3)
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"a,,,,2",
		",,,,6"
		 })
	void testFind(String name,String memberType,Boolean active ,Integer id,int size) {
		var list = service.find(name, memberType, active, id);
		logger.printTableByEntity(list, Customer.class);
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
