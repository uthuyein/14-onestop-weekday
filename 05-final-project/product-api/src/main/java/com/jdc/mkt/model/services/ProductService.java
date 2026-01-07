package com.jdc.mkt.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.api.inputs.ProductForm;
import com.jdc.mkt.api.inputs.search.SearchProductForm;
import com.jdc.mkt.api.outputs.SelectProduct;
import com.jdc.mkt.model.entities.Product;
import com.jdc.mkt.model.repositories.ProductRepo;

@Service
@Transactional(readOnly = true)
public class ProductService {

	@Autowired
	private ProductRepo repo;

	public List<SelectProduct> findBy(SearchProductForm form) {
		var list = repo.findBy( cb -> {
			
			var cq = cb.createQuery(SelectProduct.class);
			var root = cq.from(Product.class);
			
			SelectProduct.select(cb, cq, root);
			cq.where(form.where(cb, root));
		
			return cq;
		});	
		return list;
	}

	@Transactional
	public SelectProduct save(ProductForm form) {
		var p = repo.save(form.entity(null));
		return SelectProduct.from(p);

	}

	@Transactional
	public SelectProduct update(Integer id, ProductForm form) {
		var p = repo.save(form.entity(id));
		return SelectProduct.from(p);
	}

}
