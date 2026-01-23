package com.jdc.mkt.api.outputs;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import com.jdc.mkt.model.entities.Account;
import com.jdc.mkt.model.entities.Customer;
import com.jdc.mkt.model.entities.Sale;

public record SelectSale(
		UUID id,
		double discont,
		double tax,
		LocalDate saleDate,
		LocalTime saleTime,
		Account account,
		Customer customer
		) {
	
	public static SelectSale from(Sale sale) {
		return new SelectSale(sale.getId(),sale.getDiscount(),sale.getTax()
				,sale.getSaleDate()
				,sale.getSaleTime()
				,sale.getAccount()
				,sale.getCustomer());
	}

}

