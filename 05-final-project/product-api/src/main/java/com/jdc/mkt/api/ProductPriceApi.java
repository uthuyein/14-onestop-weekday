package com.jdc.mkt.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.mkt.api.inputs.ProductPriceForm;
import com.jdc.mkt.api.inputs.search.SearchProductPriceForm;
import com.jdc.mkt.api.outputs.SelectProductPrice;
import com.jdc.mkt.model.services.ProductPriceService;
import com.jdc.mkt.utils.ModificationResult;

@RestController
@RequestMapping("prices")
public class ProductPriceApi {

	@Autowired
	private ProductPriceService service;

	@GetMapping
	List<SelectProductPrice> findBy(@RequestBody(required = false) SearchProductPriceForm form){
		return service.findBy(form);
	}
	
	@GetMapping("{id}")
	SelectProductPrice findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	ModificationResult<Integer> update(@RequestParam(required = false) Integer id,@Validated @RequestBody ProductPriceForm form){		
		 return service.update(id,form);
	}
	
	
	
}
