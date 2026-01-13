/**
 * 
 */
package com.jdc.mkt.api.outputs;

import com.jdc.mkt.model.entities.Account_;
import com.jdc.mkt.model.entities.Customer;
import com.jdc.mkt.model.entities.Customer_;
import com.jdc.mkt.model.entities.Product;
import com.jdc.mkt.model.entities.ProductPrice_;
import com.jdc.mkt.model.entities.Product_;
import com.jdc.mkt.model.entities.Sale;
import com.jdc.mkt.model.entities.SaleDetail;
import com.jdc.mkt.model.entities.SaleDetail_;
import com.jdc.mkt.model.entities.Sale_;
import com.jdc.mkt.model.entities.Size;
import com.jdc.mkt.model.entities.Size_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

/**
 * SelectSale
 *
 * @author MKT
 * @created Jan 12, 2026
 * @project product-api
 */
public record SelectSaleDetail(
		Integer accountId,
		String username,
		Integer customerId,
		String customerName,
		Integer productId,
		String productName,
		Double price,
		Integer sizeId,
		String sizeName,
		Integer qty,
		Double subTotal,
		Double discount,
		Double tax,
		Double total
		
		) {

	/**
	 * @param cb
	 * @param cq
	 * @param root
	 * @param detail
	 * @param product
	 * @param size
	 * @param customer
	 * @return
	 */
	public static void select(CriteriaBuilder cb, CriteriaQuery<SelectSaleDetail> cq,
			Root<Sale> root, ListJoin<Sale, SaleDetail> detail, Path<Product> product, Path<Size> size,
			Join<Sale, Customer> customer) {
		
		cq.select(
				cb.construct(SelectSaleDetail.class,
				root.get(Sale_.account).get(Account_.id),	
				root.get(Sale_.account).get(Account_.name),
				customer.get(Customer_.id),
				customer.get(Customer_.name),
				product.get(Product_.id),
				product.get(Product_.name),
				detail.get(SaleDetail_.productPrice).get(ProductPrice_.price),
				size.get(Size_.id),
				size.get(Size_.name),
				detail.get(SaleDetail_.qty),
				detail.get(SaleDetail_.subTotal),
				root.get(Sale_.discount),
				root.get(Sale_.tax),
				root.get(Sale_.total)				
				));
	}

}
