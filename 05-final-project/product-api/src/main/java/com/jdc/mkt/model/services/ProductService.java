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
import com.jdc.mkt.utils.ModificationResult;
import com.jdc.mkt.utils.ModificationResult.ModifiedType;

@Service
@Transactional(readOnly = true)
public class ProductService {

	@Autowired
	private ProductRepo repo;
	
	public List<SelectProduct> findAll(){
		return repo.findAll().stream().map( p -> SelectProduct.from(p)).toList();
	}

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
	public ModificationResult<Integer> update(Integer id, ProductForm form) {
		var product = id != null ? repo.findById(id).orElse(null) : null;
		
		product = repo.save(form.entity(product));
		return ModificationResult.status(product.getId(), ModifiedType.Update, product.getName());
	}

	@Transactional
	public ModificationResult<Integer> save(ProductForm form) {
		var product = new Product();
		product.setActive(true);
		product = repo.save(form.entity(product));
		return ModificationResult.status(product.getId(), ModifiedType.Save, product.getName());
	}

}
