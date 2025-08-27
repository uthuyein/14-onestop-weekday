package com.jdc.mkt.test;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.services.CustomerService;

public class CustomerTest {
	
	CustomerService service = new CustomerService();

	@Test
	void testInsert() {
		var res = service.save("Aung Aung");
		System.out.println(res);
	}
}
