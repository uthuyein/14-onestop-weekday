/**
 * 
 */
package com.jdc.mkt.api.inputs;

import com.jdc.mkt.model.entities.ProductPrice;
import com.jdc.mkt.model.entities.Sale;
import com.jdc.mkt.model.entities.SaleDetail;
import com.jdc.mkt.model.entities.SaleDetailPk;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * SaleDetailForm
 *
 * @author MKT
 * @created Jan 12, 2026
 * @project product-api
 */

public record SaleDetailForm(
		@NotNull(message = "Please select product Price !")
		ProductPrice productPrice,
		@NotBlank(message =  "Please enter quantity")
		Integer qty
		) {

	/**
	 * @param sale
	 * @return
	 */
	public SaleDetail entity(Sale sale) {
		var pk = new SaleDetailPk();
		pk.setProductPriceId(productPrice.getId());
		pk.setSaleId(sale.getId());
		
		var detail = new SaleDetail();
		detail.setId(pk);
		
		detail.setQty(qty );
		return detail;
	}

}
