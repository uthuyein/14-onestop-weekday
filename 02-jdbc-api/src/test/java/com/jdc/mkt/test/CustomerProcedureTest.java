package com.jdc.mkt.test;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.services.CustomerServiceWithPreparedStatement;
import com.jdc.mkt.services.ServiceWithProcedure;

public class CustomerProcedureTest {

	private ServiceWithProcedure service  = new  CustomerServiceWithPreparedStatement();
	
	@Test
	void testCountByMemberType() {
		var count = service.countCustomerByMemberType("Silver");
		System.out.println("count :"+count);
	}
}
