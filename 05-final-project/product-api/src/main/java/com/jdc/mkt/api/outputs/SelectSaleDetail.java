/**
 * 
 */
package com.jdc.mkt.api.outputs;

import com.jdc.mkt.model.entities.SaleDetail;

import lombok.AccessLevel;
import lombok.Builder;

/**
 * SelectSale
 *
 * @author MKT
 * @created Jan 12, 2026
 * @project product-api
 */
@Builder(access = AccessLevel.PRIVATE)
public record SelectSaleDetail(
		Integer accountId,
		String username,
		Integer customerId,
		String customerName,
		Double discount,
		Double tax,
		Double total,
		Integer productId,
		String productName,
		Double price,
		Integer sizeId,
		String sizeName,
		Integer qty,
		Double subTotal
		
		) {

	/**
	 * @param sale
	 * @return SelectSaleDetail
	 */
	public static SelectSaleDetail from(SaleDetail sale) {
		return SelectSaleDetail.builder()
				.accountId(sale.getSale().getAccount().getId())
				.username(sale.getSale().getAccount().getName())
				.customerId(sale.getSale().getCustomer().getId())
				.customerName(sale.getSale().getCustomer().getName())
				.discount(sale.getSale().getDiscount())
				.tax(sale.getSale().getTax())
				.total(sale.getSale().getTotal())
				.productId(sale.getProductPrice().getProduct().getId())
				.productName(sale.getProductPrice().getProduct().getName())
				.price(sale.getProductPrice().getPrice())
				.sizeId(sale.getProductPrice().getSize().getId())
				.sizeName(sale.getProductPrice().getSize().getName())
				.qty(sale.getQty())
				.subTotal(sale.getQty()*sale.getProductPrice().getPrice())
				.build();
	}

}
