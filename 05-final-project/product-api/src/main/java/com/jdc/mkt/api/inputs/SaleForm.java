/**
 * 
 */
package com.jdc.mkt.api.inputs;

import com.jdc.mkt.model.entities.Account;
import com.jdc.mkt.model.entities.Customer;
import com.jdc.mkt.model.entities.Sale;

import jakarta.validation.constraints.NotNull;

/**
 * SaleForm
 *
 * @author MKT
 * @created Jan 12, 2026
 * @project product-api
 */

public record SaleForm(
		Integer accountId,
		@NotNull(message = "Please select customer !")
		Integer customerId,
		Double discount,
		Double tax,
		Double total
		) {

	/**
	 * @param sale
	 * @return
	 */
	public Sale entity(Sale sale) {
		
		var account = new Account();
		account.setId(accountId);
		
		var customer = new Customer();
		customer.setId(customerId);
		
		sale.setAccount(account);
		sale.setCustomer(customer);
		sale.setTax(sale.getTax());
		sale.setDiscount(sale.getDiscount());
		sale.setSaleDate(sale.getSaleDate());
		sale.setSaleTime(sale.getSaleTime());
		return sale;
	}


}
