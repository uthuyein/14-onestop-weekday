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

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class ProductPriceService {

	
	@Autowired
	private ProductPriceRepo repo;

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
	public ModificationResult<Integer> save(Integer id, ProductPriceForm form) {
		var price = id != null ?  repo.findById(id).orElse(null) : null;
		
		String mess =  null;
		
		if(price != null) {
			price = repo.save(form.entity(price));
			mess =  "%s has successfully update !";
		}else {
			price = repo.save(form.entity(new ProductPrice()));
			mess =  "%s has successfully save !";
		}
	
		return ModificationResult.success(price.getId(), mess.formatted(price.getProduct().getName()));
	}
}
