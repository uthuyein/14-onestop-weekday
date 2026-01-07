package com.jdc.mkt.model.services;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.mkt.api.inputs.ProductForm;
import com.jdc.mkt.api.inputs.search.SearchProductForm;
import com.jdc.mkt.api.outputs.SelectProduct;
import com.jdc.mkt.model.entities.Product;
import com.jdc.mkt.model.repositories.ProductRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class ProductService {

	@Autowired
	private ProductRepo repo;

	public List<Product> findBy(SearchProductForm form) {
		var list = repo.findBy(searchFunction(form));
		list.stream().forEach(
				p -> System.out.println("Category : " + p.getCategory().getName() + "\t" + "Product : " + p.getName()));
		return list;
	}

	private Function<CriteriaBuilder, CriteriaQuery<Product>> searchFunction(SearchProductForm form) {
				
//		Function<CriteriaBuilder, CriteriaQuery<Product>> fun = cb -> {
//			
//			var cq = cb.createQuery(Product.class);
//			var root = cq.from(Product.class);
//			
//			// SelectProduct.select(cb, cq, root);
//			cq.where(form.where(cb, root));
//		
//			return cq;
//		};
//		System.out.println("Function :::::::::::::::::::::"+fun);
		
		return  null;

	}

	public SelectProduct save(ProductForm form) {
		var p = repo.save(form.entity(null));
		return SelectProduct.from(p);

	}

	public SelectProduct update(Integer id, ProductForm form) {
		var p = repo.save(form.entity(id));
		return SelectProduct.from(p);
	}

}
