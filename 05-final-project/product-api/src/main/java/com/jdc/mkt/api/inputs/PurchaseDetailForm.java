package com.jdc.mkt.api.inputs;

import com.jdc.mkt.model.entities.ProductPrice;
import com.jdc.mkt.model.entities.Purchase;
import com.jdc.mkt.model.entities.PurchaseDetail;
import com.jdc.mkt.model.entities.PurchaseDetailPk;

public record PurchaseDetailForm(
		Purchase purchase,
		ProductPrice productPrice,
		Integer qty
		) {

	public PurchaseDetail entity() {
		
		var pk = new PurchaseDetailPk();
		pk.setPurchaseId(purchase.getId());
		pk.setProductPriceId(productPrice.getId());
		
		var detail = new PurchaseDetail();		
		detail.setId(pk);
		detail.setQty(qty);
		
		return detail;
	}

}
