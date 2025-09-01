package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.mkt.services.CustomerService;

public class CustomerTest {
	
	CustomerService service = new CustomerService();

	@ParameterizedTest	
	@ValueSource(strings = {"Aung Aung", "Mg Mg", "Hla Hla", "Su Su"})
	void testInsert(String name) {
		var row = service.save(name);
		assertEquals(1, row);
	}
	
	@ParameterizedTest
	@CsvSource({
		",Gold,,1" ,
		"Aung Ko Oo,,,1"
		 })
	void testUpdate(String name,String memberType,Boolean active ,int id) {
		var row = service.update(name, memberType, active, id);
		assertEquals(1, row);
	}

	@ParameterizedTest
	@CsvSource({
		",Gold,,,2" ,
		"a,,,,2"
		 })
	void testFind(String name,String memberType,Boolean active ,int id,int size) {
		var list = service.find(name, memberType, active, id);
		assertEquals(size, list.size());
	}
}
