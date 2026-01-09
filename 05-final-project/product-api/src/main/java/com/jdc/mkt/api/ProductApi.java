package com.jdc.mkt.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.mkt.api.inputs.ProductForm;
import com.jdc.mkt.api.inputs.search.SearchProductForm;
import com.jdc.mkt.api.outputs.SelectProduct;
import com.jdc.mkt.model.services.ProductService;
import com.jdc.mkt.utils.ModificationResult;

@RestController
@RequestMapping("products")
public class ProductApi {

	@Autowired
	private ProductService service;
	
	@GetMapping
	List<SelectProduct> findBy(@RequestBody SearchProductForm form){
		return service.findBy(form);
	}
		
	@PostMapping
	ModificationResult<Integer> update(@RequestParam(required = false) Integer id, @RequestBody ProductForm form){
		return service.update(id,form);
	}
}
