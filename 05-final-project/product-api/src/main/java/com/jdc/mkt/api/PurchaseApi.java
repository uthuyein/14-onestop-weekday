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

import com.jdc.mkt.api.inputs.PurchaseForm;
import com.jdc.mkt.api.inputs.search.SearchPurchaseForm;
import com.jdc.mkt.api.outputs.SelectPurchase;
import com.jdc.mkt.api.outputs.SelectPurchaseDetail;
import com.jdc.mkt.model.services.PurchaseService;
import com.jdc.mkt.utils.ModificationResult;

@RestController
@RequestMapping("purchases")
public class PurchaseApi {

	@Autowired
	private PurchaseService service;
	
	@GetMapping
	List<SelectPurchaseDetail> findBy(@RequestBody SearchPurchaseForm form){
		return service.findBy(form);
	}
	
	@GetMapping("{id}")
	SelectPurchase findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	ModificationResult<Integer> update(@RequestParam(required = false) Integer id,@Validated @RequestBody PurchaseForm form){
		return service.update(id,form);
	}
}
