package com.jdc.mkt.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.mkt.api.inputs.ProductForm;
import com.jdc.mkt.api.outputs.SelectProduct;
import com.jdc.mkt.model.services.ProductService;
import com.jdc.mkt.utils.ModificationResult;

@RestController
@RequestMapping("products")
public class ProductApi {

	@Autowired
	private ProductService service;
	
	@GetMapping
	List<SelectProduct> findBy(@RequestBody(required = false) ProductForm form){
		return service.findBy(form);
	}
	
	@PostMapping
	ModificationResult<Integer> save(@Validated @RequestBody ProductForm form){
		var product = service.save(form);
		return ModificationResult.success(product.id(), "%s has successfully save !".formatted(form.name()));
	}
	
	@PutMapping("{id}")
	ModificationResult<Integer> update(@PathVariable(required = false) Integer id, @RequestBody ProductForm form){
		var product = service.update(id,form);
		return ModificationResult.success(product.id(), "%s has successfully update !".formatted(form.name()));
	}
}
