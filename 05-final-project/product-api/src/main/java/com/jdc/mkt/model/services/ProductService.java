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
		
		ModifiedType update = product == null ? ModifiedType.Save : ModifiedType.Update;		
		product = repo.save(update == ModifiedType.Update ? form.entity(product): form.entity(new Product()));
			
		return ModificationResult.success(product.getId(),update,product.getName());
	}

}
