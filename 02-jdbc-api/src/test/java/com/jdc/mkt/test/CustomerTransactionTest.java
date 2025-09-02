
package com.jdc.mkt.test;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.services.CustomerServiceWithTransaction;

public class CustomerTransactionTest {
	
	CustomerServiceWithTransaction tran = new CustomerServiceWithTransaction();
	
	@Test
	void testTransfer() {
		tran.transfer(1, 2, 150000);
	}
}
