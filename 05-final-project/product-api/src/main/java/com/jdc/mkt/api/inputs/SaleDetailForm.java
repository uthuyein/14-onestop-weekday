/**
 * 
 */
package com.jdc.mkt.api.inputs;

import com.jdc.mkt.model.entities.ProductPrice;
import com.jdc.mkt.model.entities.Sale;
import com.jdc.mkt.model.entities.SaleDetail;

/**
 * SaleDetailForm
 *
 * @author MKT
 * @created Jan 12, 2026
 * @project product-api
 */

public record SaleDetailForm(
		Sale sale,
		ProductPrice productPrice,
		Integer qty,
		Double subTotal
		) {

	/**
	 * @param sale
	 * @return
	 */
	public SaleDetail entity(Sale sale) {
		var detail = new SaleDetail();
		detail.setSale(sale);
		detail.setProductPrice(productPrice);
		detail.setQty(qty);
		detail.setSubTotal(subTotal);
		return detail;
	}

}
