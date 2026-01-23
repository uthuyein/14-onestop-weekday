package com.jdc.mkt.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.api.inputs.ProductPriceForm;
import com.jdc.mkt.api.inputs.search.SearchProductPriceForm;
import com.jdc.mkt.api.outputs.SelectProductPrice;
import com.jdc.mkt.model.entities.ProductPrice;
import com.jdc.mkt.model.repositories.ProductPriceRepo;
import com.jdc.mkt.utils.ModificationResult;
import com.jdc.mkt.utils.ModificationResult.ModifiedType;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class ProductPriceService {

	
	@Autowired
	private ProductPriceRepo repo;
	
	public List<SelectProductPrice> findByIsActive(){
		return repo.findAll().stream().filter(c -> c.isActive())
				.map(SelectProductPrice :: from).toList();
	}

	public List<SelectProductPrice> findBy(SearchProductPriceForm form) {
		return repo.findBy(cb -> {
			var cq = cb.createQuery(SelectProductPrice.class);
			var root = cq.from(ProductPrice.class);

			SelectProductPrice.select(cb, cq, root);
			cq.where(form.where(cb, root));

			return cq;
		});
	}

	public SelectProductPrice findById(Integer id) {
		var select = repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("There is no entity found from database ! "));
		return SelectProductPrice.from(select);
	}

	@Transactional
	public ModificationResult<Integer> update(Integer id, ProductPriceForm form) {
		var product = id != null ?  repo.findById(id).orElse(null) : null;
		
		product = repo.save(form.entity(product));
		return ModificationResult.status(product.getId(), ModifiedType.Update, product.getProduct().getName());
	}

	@Transactional
	public ModificationResult<Integer> save(ProductPriceForm form) {
		var product = new ProductPrice();
		product = repo.save(form.entity(product));
		return ModificationResult.status(product.getId(), ModifiedType.Save, product.getProduct().getName());
	}

	public List<SelectProductPrice> findAll() {
		return repo.findAll().stream().map(SelectProductPrice::from).toList();
	}
}
