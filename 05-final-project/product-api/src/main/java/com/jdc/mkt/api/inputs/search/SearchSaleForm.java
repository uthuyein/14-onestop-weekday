/**
 * 
 */
package com.jdc.mkt.api.inputs.search;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.mkt.model.entities.Account_;
import com.jdc.mkt.model.entities.Category;
import com.jdc.mkt.model.entities.Category_;
import com.jdc.mkt.model.entities.Customer;
import com.jdc.mkt.model.entities.Customer_;
import com.jdc.mkt.model.entities.Product;
import com.jdc.mkt.model.entities.Product_;
import com.jdc.mkt.model.entities.Sale;
import com.jdc.mkt.model.entities.SaleDetail;
import com.jdc.mkt.model.entities.Sale_;
import com.jdc.mkt.model.entities.Size;
import com.jdc.mkt.model.entities.Size_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

/**
 * SearchSaleForm
 *
 * @author MKT
 * @created Jan 12, 2026
 * @project product-api
 */

@Data
public class SearchSaleForm {

	private String username;
	private String customerName;
	private Category category;
	private Product product;
	private Size size;
	private LocalDate dateFrom;
	private LocalDate dateTo;

	/**
	 * @param cb
	 * @param root
	 * @param detail
	 * @param product
	 * @param size
	 * @param customer
	 * @return
	 */
	public Predicate[] where(CriteriaBuilder cb, Root<Sale> root, ListJoin<Sale, SaleDetail> detail,
			Path<Product> jProduct, Path<Size> jSize, Join<Sale, Customer> jCustomer) {
		var params = new ArrayList<Predicate>();

		if (StringUtils.hasLength(username)) {
			params.add(cb.equal(root.get(Sale_.account).get(Account_.name), username));
		}
		if (StringUtils.hasLength(customerName)) {
			params.add(cb.equal(jCustomer.get(Customer_.name), customerName));
		}

		if (null != category) {
			params.add(cb.equal(jProduct.get(Product_.category).get(Category_.name), category.getName()));
		}
		if (null != product) {
			params.add(cb.equal(jProduct.get(Product_.name), product.getName()));
		}
		if (null != size) {
			params.add(cb.equal(jSize.get(Size_.name), size.getName()));
		}

		if (null != dateFrom) {
			params.add(cb.greaterThanOrEqualTo(root.get(Sale_.saleDate), dateFrom));
		}

		if (null != dateTo) {
			params.add(cb.lessThanOrEqualTo(root.get(Sale_.saleDate), dateTo));
		}

		return params.toArray(Predicate[]::new);
	}

}
