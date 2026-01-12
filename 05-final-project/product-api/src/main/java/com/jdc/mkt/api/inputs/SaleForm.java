/**
 * 
 */
package com.jdc.mkt.api.inputs;

import java.util.List;

import com.jdc.mkt.model.entities.Account;
import com.jdc.mkt.model.entities.Customer;

/**
 * SaleForm
 *
 * @author MKT
 * @created Jan 12, 2026
 * @project product-api
 */

public record SaleForm(
		Account account,
		Customer customer,
		Double discount,
		List<SaleDetailForm> saleDetails,
		Double tax,
		Double total
		) {

}
