package com.jdc.mkt.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.mkt.api.inputs.ProductPriceForm;
import com.jdc.mkt.api.inputs.search.SearchProductPriceForm;
import com.jdc.mkt.api.outputs.SelectProductPrice;
import com.jdc.mkt.model.entities.ProductPrice.PriceType;
import com.jdc.mkt.model.services.ProductPriceService;
import com.jdc.mkt.utils.ModificationResult;

@RestController
@RequestMapping("admin/prices")
public class ProductPriceApi {

	@Autowired
	private ProductPriceService service;

	@GetMapping
	List<SelectProductPrice> findAll(){
		return service.findByIsActive();
	}
	
	@GetMapping("find")
	List<SelectProductPrice> findBy(
		@RequestParam(required = false) String category,
		@RequestParam(required = false) String product,
		@RequestParam(required = false) String size,
		@RequestParam(required = false) PriceType priceType,
		@RequestParam(required = false) LocalDate dateFrom,
		@RequestParam(required = false) LocalDate dateTo){
		var form = new SearchProductPriceForm(category,product,size,priceType,dateFrom,dateTo);
		var list = service.findBy(form);
		System.out.println(list);
		return service.findBy(form);
	}
	
	@GetMapping("{id}")
	SelectProductPrice findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	ModificationResult<Integer> update(@Validated @RequestBody ProductPriceForm form){		
		 return service.save(form);
	}
	
	@PutMapping("{id}")
	ModificationResult<Integer> update(@PathVariable Integer id, @RequestBody(required = false) ProductPriceForm form ){
		return service.update(id,form);
	}
	
}
